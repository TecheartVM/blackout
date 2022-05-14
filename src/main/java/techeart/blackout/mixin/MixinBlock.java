package techeart.blackout.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import techeart.blackout.LightLevelProcessor;

@Mixin(Block.class)
public abstract class MixinBlock
{
    @Inject(method = "getLightValue(Lnet/minecraft/block/state/IBlockState;)I", at = @At("RETURN"), require = 1, cancellable = true)
    private void getLightValue(IBlockState state, CallbackInfoReturnable<Integer> cir)
    {
        int baseLight = cir.getReturnValueI();
        if(baseLight <= 0)
        {
            cir.cancel();
            return;
        }
        cir.setReturnValue(LightLevelProcessor.processLightLevel(state, baseLight));
    }
}
