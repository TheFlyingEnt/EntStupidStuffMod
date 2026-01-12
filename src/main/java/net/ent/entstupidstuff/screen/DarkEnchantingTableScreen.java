package net.ent.entstupidstuff.screen;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EnchantmentNames;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/*public class DarkEnchantingTableScreen extends HandledScreen<DarkDarkEnchantmentScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(EntStupidStuff.MOD_ID, "textures/gui/container/dark_enchanting_table.png");

    public DarkEnchantingTableScreen(DarkDarkEnchantmentScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }



    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
    }/*

    @Override
    public void submit(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }*/

    /*@Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Draw enchantment options
        for (int i = 0; i < 3; i++) {
            int cost = this.handler.enchantmentPower[i];
            if (cost > 0) {
                int optionX = x + 60;
                int optionY = y + 14 + i * 19;
                drawEnchantmentOption(context, optionX, optionY, cost, i);
            }
        }
    }*/

    

    /*private void drawEnchantmentOption(DrawContext context, int x, int y, int cost, int slot) {
        // Simple placeholder rectangle or button
        fillGradient(context, x, y, x + 108, y + 17, 0xFF_222222, 0xFF_555555);
        textRenderer.draw(context.getMatrices(), "Enchant " + cost, x + 5, y + 3, 0xFFFFFF);
    }*
}*/

//@Environment(EnvType.CLIENT)
public class DarkEnchantingTableScreen extends AbstractContainerScreen<DarkEnchantmentScreenHandler> {
	private static final ResourceLocation[] LEVEL_TEXTURES = new ResourceLocation[]{
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_1"),
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_2"),
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_3")
	};
	private static final ResourceLocation[] LEVEL_DISABLED_TEXTURES = new ResourceLocation[]{
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_1_disabled"),
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_2_disabled"),
		ResourceLocation.withDefaultNamespace("container/enchanting_table/level_3_disabled")
	};
	private static final ResourceLocation ENCHANTMENT_SLOT_DISABLED_TEXTURE = ResourceLocation.withDefaultNamespace("container/enchanting_table/enchantment_slot_disabled");
	private static final ResourceLocation ENCHANTMENT_SLOT_HIGHLIGHTED_TEXTURE = ResourceLocation.withDefaultNamespace("container/enchanting_table/enchantment_slot_highlighted");
	private static final ResourceLocation ENCHANTMENT_SLOT_TEXTURE = ResourceLocation.withDefaultNamespace("container/enchanting_table/enchantment_slot");
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/enchanting_table.png");
	private static final ResourceLocation BOOK_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/enchanting_table_book.png");
	private final RandomSource random = RandomSource.create();
	private BookModel BOOK_MODEL;
	public int ticks;
	public float nextPageAngle;
	public float pageAngle;
	public float approximatePageAngle;
	public float pageRotationSpeed;
	public float nextPageTurningSpeed;
	public float pageTurningSpeed;
	private ItemStack stack = ItemStack.EMPTY;

	public DarkEnchantingTableScreen(DarkEnchantmentScreenHandler handler, Inventory inventory, Component title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		super.init();
		this.BOOK_MODEL = new BookModel(this.minecraft.getEntityModels().bakeLayer(ModelLayers.BOOK));
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.doTick();
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent click, boolean doubled) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		for (int k = 0; k < 3; k++) {
			double d = click.x() - (i + 60);
			double e = click.y() - (j + 14 + 19 * k);
			if (d >= 0.0 && e >= 0.0 && d < 108.0 && e < 19.0 && this.menu.clickMenuButton(this.minecraft.player, k)) {
				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, k);
				return true;
			}
		}

		return super.mouseClicked(click, doubled);
	}

	@Override
	protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
		this.drawBook(context, i, j);
		EnchantmentNames.getInstance().initSeed((long)this.menu.getSeed());
		int k = this.menu.getEchoShardCount();

		for (int l = 0; l < 3; l++) {
			int m = i + 60;
			int n = m + 20;
			int o = this.menu.enchantmentPower[l];
			if (o == 0) {
				context.blitSprite(RenderPipelines.GUI_TEXTURED, ENCHANTMENT_SLOT_DISABLED_TEXTURE, m, j + 14 + 19 * l, 108, 19);
			} else {
				String string = o + "";
				int p = 86 - this.font.width(string);
				FormattedText stringVisitable = EnchantmentNames.getInstance().getRandomName(this.font, p);
				int q = 6839882;
				if ((k < l + 1 || this.minecraft.player.experienceLevel < o) && !this.minecraft.player.getAbilities().instabuild) {
					context.blitSprite(RenderPipelines.GUI_TEXTURED, ENCHANTMENT_SLOT_DISABLED_TEXTURE, m, j + 14 + 19 * l, 108, 19);
					context.blitSprite(RenderPipelines.GUI_TEXTURED, LEVEL_DISABLED_TEXTURES[l], m + 1, j + 15 + 19 * l, 16, 16);
					context.drawWordWrap(this.font, stringVisitable, n, j + 16 + 19 * l, p, ARGB.opaque((q & 16711422) >> 1), false);
					q = 4226832;
				} else {
					int r = mouseX - (i + 60);
					int s = mouseY - (j + 14 + 19 * l);
					if (r >= 0 && s >= 0 && r < 108 && s < 19) {
						context.blitSprite(RenderPipelines.GUI_TEXTURED, ENCHANTMENT_SLOT_HIGHLIGHTED_TEXTURE, m, j + 14 + 19 * l, 108, 19);
						q = -128;
					} else {
						context.blitSprite(RenderPipelines.GUI_TEXTURED, ENCHANTMENT_SLOT_TEXTURE, m, j + 14 + 19 * l, 108, 19);
					}

					context.blitSprite(RenderPipelines.GUI_TEXTURED, LEVEL_TEXTURES[l], m + 1, j + 15 + 19 * l, 16, 16);
					context.drawWordWrap(this.font, stringVisitable, n, j + 16 + 19 * l, p, q, false);
					q = -8323296;
				}

				context.drawString(this.font, string, n + 86 - this.font.width(string), j + 16 + 19 * l + 7, q);
			}
		}
	}

	private void drawBook(GuiGraphics context, int x, int y) {
		float f = this.minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
		float g = Mth.lerp(f, this.pageTurningSpeed, this.nextPageTurningSpeed);
		float h = Mth.lerp(f, this.pageAngle, this.nextPageAngle);
		int i = x + 14;
		int j = y + 14;
		int k = i + 38;
		int l = j + 31;
		context.submitBookModelRenderState(this.BOOK_MODEL, BOOK_TEXTURE, 40.0F, g, h, i, j, k, l);
	}

	@Override
	public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		this.renderTooltip(context, mouseX, mouseY);
		boolean bl = this.minecraft.player.getAbilities().instabuild;
		int i = this.menu.getEchoShardCount();

		for (int j = 0; j < 3; j++) {
			int k = this.menu.enchantmentPower[j];
			Optional<Holder.Reference<Enchantment>> optional = this.minecraft
				.level
				.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.get(this.menu.enchantmentId[j]);
			if (!optional.isEmpty()) {
				int l = this.menu.enchantmentLevel[j];
				int m = j + 1;
				if (this.isHovering(60, 14 + 19 * j, 108, 17, (double)mouseX, (double)mouseY) && k > 0 && l >= 0 && optional != null) {
					List<Component> list = Lists.<Component>newArrayList();
					list.add(Component.translatable("container.enchant.clue", Enchantment.getFullname((Holder<Enchantment>)optional.get(), l)).withStyle(ChatFormatting.WHITE));
					if (!bl) {
						list.add(CommonComponents.EMPTY);
						if (this.minecraft.player.experienceLevel < k) {
							list.add(Component.translatable("container.enchant.level.requirement", this.menu.enchantmentPower[j]).withStyle(ChatFormatting.RED));
						} else {
							MutableComponent mutableText;
							if (m == 1) {
								mutableText = Component.translatable("container.enchant.lapis.one");
							} else {
								mutableText = Component.translatable("container.enchant.lapis.many", m);
							}

							list.add(mutableText.withStyle(i >= m ? ChatFormatting.GRAY : ChatFormatting.RED));
							MutableComponent mutableText2;
							if (m == 1) {
								mutableText2 = Component.translatable("container.enchant.level.one");
							} else {
								mutableText2 = Component.translatable("container.enchant.level.many", m);
							}

							list.add(mutableText2.withStyle(ChatFormatting.GRAY));
						}
					}

					context.setComponentTooltipForNextFrame(this.font, list, mouseX, mouseY);
					break;
				}
			}
		}
	}

	public void doTick() {
		ItemStack itemStack = this.menu.getSlot(0).getItem();
		if (!ItemStack.matches(itemStack, this.stack)) {
			this.stack = itemStack;

			do {
				this.approximatePageAngle = this.approximatePageAngle + (float)(this.random.nextInt(4) - this.random.nextInt(4));
			} while (this.nextPageAngle <= this.approximatePageAngle + 1.0F && this.nextPageAngle >= this.approximatePageAngle - 1.0F);
		}

		this.ticks++;
		this.pageAngle = this.nextPageAngle;
		this.pageTurningSpeed = this.nextPageTurningSpeed;
		boolean bl = false;

		for (int i = 0; i < 3; i++) {
			if (this.menu.enchantmentPower[i] != 0) {
				bl = true;
			}
		}

		if (bl) {
			this.nextPageTurningSpeed += 0.2F;
		} else {
			this.nextPageTurningSpeed -= 0.2F;
		}

		this.nextPageTurningSpeed = Mth.clamp(this.nextPageTurningSpeed, 0.0F, 1.0F);
		float f = (this.approximatePageAngle - this.nextPageAngle) * 0.4F;
		float g = 0.2F;
		f = Mth.clamp(f, -0.2F, 0.2F);
		this.pageRotationSpeed = this.pageRotationSpeed + (f - this.pageRotationSpeed) * 0.9F;
		this.nextPageAngle = this.nextPageAngle + this.pageRotationSpeed;
	}
}
