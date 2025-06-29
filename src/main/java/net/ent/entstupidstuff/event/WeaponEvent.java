package net.ent.entstupidstuff.event;

import net.ent.entstupidstuff.api.weaponTrait.TwoHandTrait;
import net.ent.entstupidstuff.event.callback.AttackCallbackAll;
//import net.ent.entstupidstuff.api.weaponTrait.BleedingTrait;
import net.ent.entstupidstuff.api.weaponTrait.BluntTrait;
import net.ent.entstupidstuff.api.weaponTrait.CircleSlashTrait;

//import net.ent.entstupidstuff.api.IntTrait.IBleedingTrait;
import net.ent.entstupidstuff.api.IntTrait.IBluntTrait;
import net.ent.entstupidstuff.api.IntTrait.ICircleSlashTrait;
import net.ent.entstupidstuff.api.IntTrait.ITwoHandTrait;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;


public class WeaponEvent {

    

    

    public static void onInitialize() {
        AttackCallbackAll.EVENT.register((attacker, world, hand, entity, hitResult) -> {

            ItemStack mainHandStack = attacker.getMainHandStack();
            Item mainHandItem = mainHandStack.getItem();

            if (entity instanceof LivingEntity && attacker instanceof PlayerEntity) {

                //Running only for player
                PlayerEntity player = (PlayerEntity) attacker;
                float baseDamage = (float) attacker.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);

                if (mainHandItem instanceof ITwoHandTrait) {
                    System.out.println("Applying Effect: TwoHand");

                    if (TwoHandTrait.isUsingTwoHands(attacker)) {
                        System.out.println("     - isUsingTwoHand = True");
                        TwoHandTrait.applyDamageReductionOG(attacker, true, baseDamage);
                        TwoHandTrait.applyMiningFatigue(attacker);

                    } else {
                        System.out.println("     - isUsingTwoHand = False");
                        TwoHandTrait.applyDamageReductionOG(attacker, false, baseDamage);
                    }

                }

                //@Method Deprecated
                if (mainHandItem instanceof IBluntTrait /*&& player2.getAttackCooldownProgress(0.5f) == 1.0f*/) {
                    System.out.println("Applying Effect: Blunt");

                    BluntTrait.applyBluntEffect(attacker, (LivingEntity) entity);

                    if (world.isClient) { // Ensure this runs only on the client side
                        Vec3d pos = player.getPos();
                        world.addParticle(ParticleTypes.SONIC_BOOM, pos.x, pos.y + 1.0, pos.z, 0.0, 0.0, 0.0);
                    }
                }

                //@Method Deprecated
                if (mainHandItem instanceof ICircleSlashTrait) {
                    System.out.println("Applying Effect: Circle");

                    if (CircleSlashTrait.canPerformCircleSlash(player)) {
                        //CircleSlashTrait.performCircleSlash(player2, 2);

                        /*for (int i = 0; i < 360; i += 10) {
                            Vec3d playerPos = player2.getPos();
                            double angle = Math.toRadians(i);
                            double offsetX = playerPos.x + 2 * Math.cos(angle);
                            double offsetY = playerPos.y + 1; // Adjust Y position as needed
                            double offsetZ = playerPos.z + 2 * Math.sin(angle);
                
                            // Spawn particles
                            world.addParticle(ParticleTypes.FLAME, offsetX, offsetY, offsetZ, 0, 0, 0);
                        }*/
                    }

                }

                



            }
            else if (entity instanceof LivingEntity && attacker instanceof LivingEntity/*PlayerEntity*/) {

                //Running only for Living Entitys
                // Added an If then statement which should work when an entity does not have a GENERIC_ATTACK_DAMAGE

                EntityAttributeInstance attackDamageAttribute = attacker.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                float baseDamage = 0;

                if (attackDamageAttribute != null) {
                    baseDamage = (float) attacker.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);

                    if (mainHandItem instanceof ITwoHandTrait) {

                        System.out.println("Applying Effect: TwoHand");
    
                        if (TwoHandTrait.isUsingTwoHands(attacker)) {
                            System.out.println("     - isUsingTwoHand = True");
                            TwoHandTrait.applyDamageReductionOG(attacker, true, baseDamage);
                            TwoHandTrait.applyMiningFatigue(attacker);
                        }
                        else {
                            System.out.println("     - isUsingTwoHand = False");
                            TwoHandTrait.applyDamageReductionOG(attacker, false, baseDamage);
                        }
                    }
    
                    if (mainHandItem instanceof IBluntTrait) {
                        System.out.println("Applying Effect: Blunt");
                        BluntTrait.applyBluntEffect(attacker, (LivingEntity) entity);
                    }
                }

            }

            //Enchantment Logic

            /*DynamicRegistryManager drm = world.getRegistryManager();
            Registry<Enchantment> reg = drm.get(RegistryKeys.ENCHANTMENT);
            Optional<Reference<Enchantment>> optional = reg.getEntry(EnchantmentFactory.FROSTBITE); 
            RegistryEntry<Enchantment> registryEntry2 = optional.orElseThrow();

            System.out.println(EnchantmentHelper.getLevel(registryEntry2, mainHandStack)); // This Works


            if (EnchantmentHelper.getLevel(EntEnchantmentHelper.getEnchantments(world, EnchantmentFactory.WALL_JUMP), mainHandStack) > 0) {

                //Wall Jump is now Handled in PlayerMixin

            }

            if (EnchantmentHelper.getLevel(registryEntry2, mainHandStack) > 0) {
                
                /** Applies to LongSword and Swords, Incompatiable with Fire Ascept
                 * Frostbite I - Freeze for 10 Seconds + Hidden Slowness
                 * Frostbite II - Freeze for 15 Seconds + Hidden Slowness
                 */

                 /** Applies to Axes and Battle Axes - Operates like a Trait
                 * Berserk I - 10/15/25% Damage Increase
                 * Berserk II - 15/50/40% Damage Increase
                 * Berserk II - 25/35/50% Damage Increase
                 */

                 /** Applies to Bows and Long Bows
                 * Arrow Rain I - Summon in 3 Arrows on hit
                 * Arrow Rain II - Summon in 5 Arrows on hit
                 */

                 /** Applies to Cross Bow + Heavy Cross bow
                 * Focus I - Projectile_Spread -5
                 * Focus II - Projectile_Spread -5
                 * Focus III - Projectile_Spread -5
                 */

                 /** Boots
                 * Double Jump I - +1 Block Jump
                 * Double Jump II - +3 Block Jump
                 */
                
            //}

















            /*if (entity instanceof LivingEntity && player instanceof LivingEntity/*PlayerEntity) {
                //System.out.println(player.getDisplayName() + " attack Detected");

                //ItemStack mainHandStack = player.getMainHandStack();
                //Item mainHandItem = mainHandStack.getItem(); //Getting Item

                //player.getAttackCooldownProgress(2f);

                //Implementation of Legacy Code

                float baseDamage = (float) player.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);

                if (mainHandItem instanceof ITwoHandTrait) {

                    System.out.println("Applying Effect: TwoHand");

                    if (TwoHandTrait.isUsingTwoHands(player)) {
                        System.out.println("     - isUsingTwoHand = True");
                        TwoHandTrait.applyDamageReductionOG(player, true, baseDamage);
                        TwoHandTrait.applyMiningFatigue(player);
                    }
                    else {
                        System.out.println("     - isUsingTwoHand = False");
                        TwoHandTrait.applyDamageReductionOG(player, false, baseDamage);
                    }
                }

                if (mainHandItem instanceof IBluntTrait) {
                    System.out.println("Applying Effect: Blunt");
                    BluntTrait.applyBluntEffect(player, (LivingEntity) entity);
                }

                /*if (mainHandItem instanceof IBleedingTrait) {
                    System.out.println("Applying Effect: Bleed Effect");
                    BleedingTrait.applyBleedingEffect(player, (LivingEntity) entity, baseDamage, true);
                }
                else {
                    BleedingTrait.applyBleedingEffect(player, (LivingEntity) entity, baseDamage, false);
                }

                //System.out.println(" ");


                //Enchantment Checkers:

                if (hasEnchantmentTest(mainHandStack)) {
                    // Do something if the item has the Frostbite enchantment
                    System.out.println("THIS RAN YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }


                return ActionResult.PASS;

            }*/

            















            /*if (entity instanceof LivingEntity && player instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) player;
                LivingEntity target = (LivingEntity) entity;
                ItemStack mainHandStack = playerEntity.getMainHandStack();
                Item mainHandItem = mainHandStack.getItem();

                //Pre-Trait Damage:
                float baseDamage = (float) playerEntity.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);
                System.out.println(baseDamage);


                if (mainHandItem instanceof ITwoHandTrait && TwoHandTrait.isUsingTwoHands(playerEntity)) {
                    baseDamage = TwoHandTrait.applyDamageReduction(playerEntity, target, baseDamage);
                    TwoHandTrait.applyMiningFatigue(playerEntity);
                }
                
                if (mainHandItem instanceof IBluntTrait){
                    BluntTrait.applyBluntEffect(playerEntity, target);
                    world.addParticle(ParticleTypes.CRIT, target.getX(), target.getY(), target.getZ(), 0.0D, 0.0D, 0.0D);
                }

                if (mainHandItem instanceof IBleedingTrait) {
                    baseDamage = BleedingTrait.applyBleedingEffect(player, target, baseDamage);
                }

                target.damage(playerEntity.getDamageSources().playerAttack((PlayerEntity) playerEntity), baseDamage);

                // Cancel the attack cooldown and return success
                playerEntity.resetLastAttackedTicks();

                return ActionResult.PASS;*/
            
            return ActionResult.PASS;
        });
        
    }

    /*public boolean hasEnchantment(ItemStack itemStack, Enchantment enchantment) {
        //ItemEnchantmentsComponent test = EnchantmentHelper.getEnchantments(itemStack);
        //EnchantmentHelper.get
        //return EnchantmentHelper.get(itemStack).containsKey(enchantment);

        return EnchantmentHelper.getLevel(enchantment, itemStack) > 0;

        //EnchantmentHelper.hasAnyEnchantmentsIn( itemStack, EnchantmentFactory.BERSERK);
    }*/

    /*public boolean hasEnchantment(ItemStack itemStack, RegistryEntry<Enchantment> enchantment) {
        return EnchantmentHelper.getLevel(enchantment, itemStack) > 0;
    }*/

    

    
}
