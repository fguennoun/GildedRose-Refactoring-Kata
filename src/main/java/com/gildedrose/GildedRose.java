package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.getName()) {
                case "Aged Brie" -> updateAgedBrie(item);
                case "Sulfuras" -> updateSulfuras(item);
                case "Backstage passes" -> updateBackstagePasses(item);
                case "Conjured" -> updateConjured(item);
                default -> updateNormalItem(item);
            }
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
        item.setSellIn(item.getSellIn() - 1);
        if (item.getSellIn() < 0 && item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void updateBackstagePasses(Item item) {
        if (item.getSellIn() <= 0) {
            handleExpiredBackstagePass(item);
        } else {
            increaseBackstagePassQuality(item);
        }
        item.setSellIn(item.getSellIn() - 1);
    }

    private void handleExpiredBackstagePass(Item item) {
        item.setQuality(0);
    }

    private void increaseBackstagePassQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
            if (item.getSellIn() <= 10) {
                increaseQualityIfBelowMax(item);
            }
            if (item.getSellIn()  <= 5) {
                increaseQualityIfBelowMax(item);
            }
        }
    }

    private void increaseQualityIfBelowMax(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void updateConjured(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 2);
            if (item.getQuality() < 0) {
                item.setQuality(0);
            }
        }
        item.setSellIn(item.getSellIn() - 1);
        if (item.getSellIn() < 0 && item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 2);
            if (item.getQuality() < 0) {
                item.setQuality(0);
            }
        }
    }

    private void updateNormalItem(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
        item.setSellIn(item.getSellIn() - 1);
        if (item.getSellIn() < 0 && item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void updateSulfuras(Item item) {
        // Sulfuras is legendary and does not change
    }
}
