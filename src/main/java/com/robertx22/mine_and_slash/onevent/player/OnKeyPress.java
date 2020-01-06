package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
import com.robertx22.mine_and_slash.gui.map_info_gui.MapInfoGui;
import com.robertx22.mine_and_slash.gui.stat_point_screen.StatPointScreen;
import com.robertx22.mine_and_slash.gui.stats_gui.StatOverviewGUI;
import com.robertx22.mine_and_slash.gui.talent_tree_gui.PerkTreeScreen;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class OnKeyPress {

    static boolean down;

    // they said i should use clienttickevent but idk how
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        Minecraft mc = Minecraft.getInstance();

        int key = event.getKey();

        if (event.getAction() == GLFW.GLFW_RELEASE) {

            if (mc.currentScreen == null) { // public net.minecraft.client.gui.screen.Screen field_71462_r

                if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new StatOverviewGUI());
                } else if (key == KeybindsRegister.Player_Stat_Points.getKey()
                        .getKeyCode()) {
                    mc.displayGuiScreen(new StatPointScreen());
                } else if (key == KeybindsRegister.Talent_Tree.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new PerkTreeScreen());
                } else if (key == KeybindsRegister.mapInfo.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new MapInfoGui());
                }

                boolean wasDown = down;
                down = KeybindsRegister.disableNeatOverlay.isKeyDown();
                if (mc.isGameFocused() && down && !wasDown)
                    NeatConfig.draw = !NeatConfig.draw;

            } else {

                if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof StatOverviewGUI) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.Player_Stat_Points.getKey()
                        .getKeyCode()) {
                    if (mc.currentScreen instanceof StatPointScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.Talent_Tree.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof PerkTreeScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.mapInfo.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof MapInfoGui) {
                        mc.displayGuiScreen(null);
                    }
                }

            }
        }

    }
}
