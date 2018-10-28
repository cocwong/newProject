package com.example.cocwong.test.bean;

import java.util.List;

public class LatestBean {
    private boolean hasMore;
    private int page;
    private int totalCount;
    private int totalPage;
    private List<ListBean> list;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * summary : DotA（刀塔）是电子竞技的传奇项目，在十年前曾是世界上最火爆的电子游戏之一，每个网吧都能找到一大群组团打DotA的少年。十年过去了，DotA变成了刀塔2，一个世界历史上奖金最高的游戏项目。当年的少年也许已经不再打Dota2，但每年的Ti他们依然如往日一样观战，dota已经成为一代人的信仰。
         * image : http://cdn.goosetalk.com/up/files/14/25/26/22/154000739464436.jpg?x-oss-process=image/quality,q_70
         * pubTime : 1540007835000
         * id : 10172
         * title : 刀塔简史
         * type : 游戏
         * points : ["Dota是怎么发展的？","为什么中国玩家以Dota为傲？","LGD为什么被人喷?"]
         */

        private String summary;
        private String image;
        private long pubTime;
        private int id;
        private String title;
        private String type;
        private List<String> points;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getPubTime() {
            return pubTime;
        }

        public void setPubTime(long pubTime) {
            this.pubTime = pubTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getPoints() {
            return points;
        }

        public void setPoints(List<String> points) {
            this.points = points;
        }
    }
}
