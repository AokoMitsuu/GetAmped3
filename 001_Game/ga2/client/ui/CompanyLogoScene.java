package ga2.client.ui;

import ga2.client.BGMManager;
import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.data.InitialItemsData;
import ga2.data.TournamentData;
import ga2.data.UserItems;
import ga2.data.net.ChannelInfo;
import ga2.setting.GameSetting;
import kotori.geom.Vector2D;
import kotori.kwt.Container;
import kotori.kwt.KWTStruct;
import kotori.kwt.KWTStructHolder;

public class CompanyLogoScene {
    ChannelInfo ci;
    Container cnt;
    Exception lastex;

    public CompanyLogoScene() throws Exception {
        LookAndFeel laf = LookAndFeel.getLAF();
        String kwtname = "panel/companylogo.kwt";
        KWTStruct srt = ((KWTStructHolder) GameSetting.getSetting().getLocalContent(kwtname)).getKWTStruct();
        this.cnt = srt.createContainer(srt.getLibrary());
        Vector2D v = laf.getSizeDimension();
        this.cnt.p.setPosition(v.x * 0.5f, v.y * 0.5f);
        this.cnt.p.sx = v.x / this.cnt.bnd.w;
        this.cnt.p.sy = this.cnt.p.sx;
        this.cnt.updateMatrix();
    }

    public void start(final GetAmped2 ga22) {
        ga22.getRootPanel().exchangePanel(this.cnt, AmpedRootPanel.LAYER_MAIN);
        BGMManager.playBGM("menu", false);
        GameSetting set = GameSetting.getSetting();
        set.version = -1;
        set.lanplayenabled = false;
        set.sdoa = false;

        for (int i = 0, len = set.tour.length; i < len; i++) {
            TournamentData tData = set.tour[i];
            if (tData == null)
                continue;
            if (tData.minplayers == 4)
                tData.minplayers = 2;
            if (tData.minplayers == 3)
                tData.minplayers = 2;
        }

        InitialItemsData initialItemsData = new InitialItemsData();
        initialItemsData.gamemoney = Integer.MAX_VALUE / 2;
        initialItemsData.realmoney = Integer.MAX_VALUE / 2;
        initialItemsData.items = UserItems.getFullItems(true).getItems();
        set.initialitems = initialItemsData;
        LanPlayWindow lanplay = new LanPlayWindow();
        lanplay.show(this.cnt.getKWTManager());
    }
}