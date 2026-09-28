package com.example; // 注意：这个包名必须和你文件所在的文件夹路径完全一致！

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // 监听玩家攻击实体的事件
        AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, net.minecraft.util.hit.EntityHitResult hitResult) -> {
            // 只在服务端执行逻辑，且目标必须是活体生物
            if (!world.isClient && entity instanceof LivingEntity) {
                // 直接造成极大伤害实现秒杀
                entity.damage(DamageSource.player(player), Float.MAX_VALUE);
            }
            return ActionResult.PASS;
        });
    }
}
