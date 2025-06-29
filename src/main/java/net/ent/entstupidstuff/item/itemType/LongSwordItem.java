package net.ent.entstupidstuff.item.itemType;

import java.util.List;

import net.ent.entstupidstuff.api.IntTrait.ICircleSlashTrait;
import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.ent.entstupidstuff.api.IntTrait.ITwoHandTrait;
import net.ent.entstupidstuff.api.weaponTrait.CircleSlashTrait;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;




public class LongSwordItem extends SwordItem  implements ITwoHandTrait, ITrait, ICircleSlashTrait{

    //public float attack = 4;

    public LongSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(toolMaterial, (5/*3.5*/)  + toolMaterial.getAttackDamage(), -2.6f, 1, 1, 0)));
        
        //material, baseAttackDamage (Added), attackSpeed (Added), toolReach (Added), attackSweep (Added)

    }

    // For 1.20.5+ Adding TwoHanded ToolTip
    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
    }

    /*@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}*/

    /* Experimental Code */

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (world.isClient && hand == Hand.MAIN_HAND) {
            if (CircleSlashTrait.canPerformCircleSlash(player)) {
                if (player.getWorld().isClient()) {
                    for (int i = 0; i < 360; i += 10) {
                        /*Vec3d playerPos = player.getPos();
                        double angle = Math.toRadians(i);
                        double offsetX = playerPos.x + 1 * Math.cos(angle);
                        double offsetY = playerPos.y + 1; // Adjust Y position as needed
                        double offsetZ = playerPos.z + 1 * Math.sin(angle);
            
                        // Spawn particles
                        world.addParticle(ParticleTypes.SWEEP_ATTACK, offsetX, offsetY, offsetZ, 0, 0, 0);*/
                        createParticleArc(world, player);
                    }
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1));
        
                }
            }
        }

        if (!world.isClient && hand == Hand.MAIN_HAND) {
            if (CircleSlashTrait.canPerformCircleSlash(player)) {
                CircleSlashTrait.performCircleSlash(player, 2.0); // Perform the circle slash with a 2-block radius               

                return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
            } else {
                return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand)); // Indicate failure if on cooldown
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(hand));
    }

    private void createParticleArc(World world, PlayerEntity player) {
        // Arc parameters

        System.out.println("Sweap Attack Preformed - Particles");

        Vec3d startPos = player.getPos().add(0, player.getStandingEyeHeight(), 0);
        double radius = 1.5;
        int particles = 20;

        for (int i = 0; i < particles; i++) {
            double angle = MathHelper.lerp((double) i / (particles - 1), -Math.PI / 2, Math.PI / 2);
            double x = -Math.sin(angle) * radius;
            double y = Math.sin(angle / 2) * radius / 2;
            double z = Math.cos(angle) * radius;

            Vec3d particlePos = startPos.add(x, y, z);
            world.addParticle(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 0, 0.01, 0);
        }
    }

    
}
