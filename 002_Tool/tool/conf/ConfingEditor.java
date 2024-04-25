
package tool.conf;

import ga2.setting.GameSetting;
import ga2.stage.StageConnector;
import kotori.gfx.GLConstants;
import kotori.kwt.CheckboxGroup;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.basic.ToggleButton;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.util.PropertyManager;
import tool.KWTApp;

public class ConfingEditor
        extends KWTApp
        implements GLConstants {
    RootPanel root;
    GameSetting gamesetting;
    StageConnector con;
    public static String GAME_CONF = "conf/game.conf";

    public static final int MOTION_MAX = 100;

    static PropertyManager pr;

    protected boolean arguments(String[] args) {
        return true;
    }

    protected String getTitle() {
        return "コンフィングエディター";
    }

    protected Container createRoot() {
        this.root = new RootPanel();
        return this.root;
    }

    protected boolean isResizable() {
        return true;
    }

    protected int getWidth() {
        return 1024;
    }

    protected int getHeight() {
        return 878;
    }

    protected void startProc() {
    }

    public RootPanel getRootPanel() {
        return this.root;
    }

    public class RootPanel
            extends Container
            implements GLConstants, ActionListener {
        private boolean isCreatePanel = false;

        private ToggleButton btn_npc;

        private ToolConfingEditorWindow1 editorwindow;

        RootPanel() {
            setRect(0.0F, 0.0F, ConfingEditor.this.getWidth(), ConfingEditor.this.getHeight());

            CheckboxGroup lg = new CheckboxGroup();

            this.btn_npc = new ToggleButton("アクセサリエディター", lg);
            this.btn_npc.addActionListener(this);
            this.btn_npc.setSelected(true);
            add((Component) this.btn_npc);

            this.editorwindow = new ToolConfingEditorWindow1(this);
            this.editorwindow.setVisible(true);
            add((Component) this.editorwindow);

            this.isCreatePanel = true;
            ToolConfingEditorFileManager.FileWrite();
        }

        public void updateLayout() {
            if (this.isCreatePanel) {

                this.editorwindow.setRect(7.0F, 40.0F, this.bnd.w - 12.0F, this.bnd.h - 60.0F);

                this.btn_npc.setRect(5.0F, 5.0F, 150.0F, 28.0F);
            }
        }

        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();

            if (src == this.btn_npc)
                this.editorwindow.setVisible(true);
        }
    }
}