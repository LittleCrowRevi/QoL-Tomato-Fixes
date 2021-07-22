package littlecrow.block.trashbin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BoxScreen extends HandledScreen<ScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/generic_54.png");

    public BoxScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    /*
    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {

        this.textRenderer.draw(matrices, this.title.asString(), 8.0f, 6.0f, 4210752);
        this.textRenderer.draw(matrices, this.playerInventory.getDisplayName(), 8.0f, (float) this.backgroundHeight - 96 + 2, 4210752);
    } */

    @SuppressWarnings("deprecation")
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        client.getTextureManager().bindTexture(TEXTURE);
        int i = (width - backgroundWidth) / 2;
        int j = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, backgroundWidth, backgroundHeight / 2);
        this.drawTexture(matrices, i, j + 3 * 18 + 16, 0, 126, this.backgroundWidth, 96  );
        // matrices, i, j / 2, 0, 0, backgroundWidth,
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
