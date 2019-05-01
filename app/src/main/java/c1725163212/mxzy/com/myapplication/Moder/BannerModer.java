package c1725163212.mxzy.com.myapplication.Moder;

import java.util.ArrayList;

public class BannerModer {
    public String date;
    public ArrayList<Stories> stories;
    public ArrayList<Top_stories> top_stories;
    public class Stories{
        public ArrayList<String> images;
        public int type;
        public String id;
        public String ga_prefix;
        public String title;
        public String multipic;

    }
    public class Top_stories{
        public String image;
        public int type;
        public String id;
        public String ga_prefix;
        public String title;
        public String multipic;
    }
}
