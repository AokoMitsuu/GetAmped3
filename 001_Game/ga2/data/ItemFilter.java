package ga2.data;

import ga2.setting.GameSetting;

public interface ItemFilter {
    boolean filter(UserItems.Item item);

    public static class Not implements ItemFilter {
        final ItemFilter f;

        public Not(ItemFilter f) {
            this.f = f;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            return !this.f.filter(i);
        }
    }

    public static class And implements ItemFilter {
        final ItemFilter[] fs;

        public And(ItemFilter... fs) {
            this.fs = fs;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            for (ItemFilter f : this.fs) {
                if (f != null && !f.filter(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Or implements ItemFilter {
        final ItemFilter[] fs;

        public Or(ItemFilter... fs) {
            this.fs = fs;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            for (ItemFilter f : this.fs) {
                if (f != null && f.filter(i)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Kind implements ItemFilter {
        final int kind;

        public Kind(int k) {
            this.kind = k;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            return i.kind == this.kind && i.getAmpedItem() != null;
        }
    }

    public static class KindList implements ItemFilter {
        final int[] kind;

        public KindList(int[] k) {
            this.kind = k;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            int l = 0;
            while (true) {
                int[] iArr = this.kind;
                if (l >= iArr.length) {
                    return false;
                }
                if (iArr[l] == i.kind) {
                    return i.getAmpedItem() != null;
                }
                l++;
            }
        }
    }

    public static class Privately implements ItemFilter {
        private static final ItemFilter notPrivately;
        private static final Privately privately;

        static {
            Privately privately2 = new Privately();
            privately = privately2;
            notPrivately = new Not(privately2);
        }

        private Privately() {
        }

        public static Privately get() {
            return privately;
        }

        public static ItemFilter not() {
            return notPrivately;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            return (i.kind == 7 && i.id == 141) || GameSetting.getSetting().isPrivately(i.kind, i.id);
        }
    }

    public static class Equipable implements ItemFilter {
        final UserItems items;
        final UserInfo ui;

        public Equipable(UserInfo ui, UserItems items) {
            this.ui = ui;
            this.items = items;
        }

        public Equipable(UserInfo ui) {
            this(ui, null);
        }

        @Override
        public boolean filter(UserItems.Item i) {
            GameSetting set = GameSetting.getSetting();
            boolean z = false;
            switch (i.kind) {
                case 1:
                    StyleData.Head h = set.getHead(i.id);
                    if (i.id == 0 || h == null || h.isWearable(this.ui.style)) {
                        z = true;
                    }
                    boolean res = z;
                    return res;
                case 2:
                    StyleData.Face f = set.getFace(i.id);
                    if (f == null || f.isWearable(this.ui.style)) {
                        z = true;
                    }
                    boolean res2 = z;
                    return res2;
                case 3:
                    StyleData.Body b = set.getBody(i.id);
                    if (b == null || b.isWearable(this.ui.style)) {
                        z = true;
                    }
                    boolean res3 = z;
                    return res3;
                case 4:
                    StyleData.Decoration d = set.getDecoration(i.id);
                    if (d == null || d.isWearable(this.ui.style)) {
                        z = true;
                    }
                    boolean res4 = z;
                    return res4;
                case 5:
                    return true;
                case 17:
                    UserItems userItems = this.items;
                    if (userItems != null && userItems.isEquipableRank(i, this.ui.isGM())) {
                        z = true;
                    }
                    boolean res6 = z;
                    return res6;
                default:
                    return true;
            }
        }
    }

    public static class SlotEquipable implements ItemFilter {
        final int style;

        public SlotEquipable(int style) {
            this.style = style;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            AmpedItem ai = i.getAmpedItem();
            if (ai instanceof StyleData.SlotItem) {
                return ((StyleData.SlotItem) ai).isSlotEquipable(this.style);
            }
            return false;
        }
    }

    public static class Expired implements ItemFilter {
        final ItemAdditionalData data;

        public Expired(ItemAdditionalData data) {
            this.data = data;
        }

        @Override
        public boolean filter(UserItems.Item i) {
            ItemAdditionalData itemAdditionalData = this.data;
            return itemAdditionalData != null && itemAdditionalData.isExpired(i);
        }
    }

    public static class InformExpired implements ItemFilter {
        final ItemAdditionalData data;
        final int time;

        public InformExpired(ItemAdditionalData data, int time) {
            this.data = data;
            this.time = time;
            if (data != null) {
                data.resetNexttimeRequireInformExpired();
            }
        }

        @Override
        public boolean filter(UserItems.Item i) {
            ItemAdditionalData itemAdditionalData = this.data;
            return itemAdditionalData != null && itemAdditionalData.isRequiredInformExpired(i, this.time);
        }
    }

    public static class Editable implements ItemFilter {
        @Override
        public boolean filter(UserItems.Item i) {
            GameSetting set = GameSetting.getSetting();
            switch (i.kind) {
                case 1:
                    StyleData.Head h = set.getHead(i.id);
                    return (h == null || h.editdisable) ? false : true;
                case 2:
                    StyleData.Face f = set.getFace(i.id);
                    return (f == null || f.editdisable) ? false : true;
                case 3:
                    StyleData.Body b = set.getBody(i.id);
                    return (b == null || b.editdisable) ? false : true;
                case 4:
                    StyleData.Decoration d = set.getDecoration(i.id);
                    return (d == null || d.editdisable) ? false : true;
                default:
                    return true;
            }
        }
    }
}