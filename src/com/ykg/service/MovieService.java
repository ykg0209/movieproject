/**
 * User: 杨
 * Date: 2020/12/2
 * Time: 21:35
 * Class MovieService
 */

package com.ykg.service;

import com.ykg.model.Movie;

import java.text.Collator;
import java.util.*;

public class MovieService {
    //保存电影的集合
    private List<Movie> movies = new ArrayList<>();

    //影片排序类别
    private static final int SORT_BY_MOVIENAME = 1;           //按名称
    private static final int SORT_BY_MOVIEDATE = 2;           //按上映日期
    private static final int SORT_BY_MOVIETYPE = 3;           //按类型
    private static final int SORT_BY_MOVIECLICKRATE = 4;      //按点击率
    private static final int SORT_BY_MOVIERECOMMENDRATE = 5;  //按推荐率

    {
        movies.add(new Movie(11160489,"肖申克的救赎","剧情","蒂姆罗宾斯","费兰特",new Date(),3,13));
        movies.add(new Movie(89131630,"霸王别姬","剧情","张国荣","陈凯歌",new Date(),2,6));
        movies.add(new Movie(89131630,"阿甘正传","喜剧","汤姆","陈罗伯特",new Date(),1,8));
        movies.add(new Movie(89131630,"泰坦尼克号","爱情","莱安娜多","詹姆斯",new Date(),4,21));
    }
    public List<Movie> getMovies(){
        return this.movies;
    }

    /**
     * 添加电影
     * @param movie
     * @return true
     */
    public boolean addMovie(Movie movie){
        this.movies.add(movie);
        return true;
    }

    /**
     * 删除影片
     * @param index
     * @return
     */
    public boolean removeMovie(int index){
        Movie remove = this.movies.remove(--index);
        return true;
    }

    /**
     * 修改影片
     * @param index 影片序号
     * @param movie 影片对象
     * @return true
     */
    public boolean updateMovie(int index,Movie movie){
        this.movies.set(--index,movie);
        return true;
    }

    /**
     * 影片按照指定属性排序
     * @param c 指定的属性的序号
     * @param movie 需要排序的影片集合
     */
    public List<Movie> sortMovies(char c,List<Movie> movie){
        // char类型转为int类型（int = char - 48）
        int i = c - 48;
        if (this.SORT_BY_MOVIENAME == i){
            //使用lambda表达式，使用Collections.sort方法进行排序
                                 //按照名称的拼音，所以需要使用Collator指定中国的语言   传入两个比较的字符串
            Collections.sort(movie,(x,y) -> Collator.getInstance(Locale.CHINA).compare(x.getMovieName(),y.getMovieName()));

        }else if (this.SORT_BY_MOVIEDATE == i){
            //日期里面有compareTo方法，直接调用     降序
            Collections.sort(movie,(x,y) -> y.getMovieDate().compareTo(x.getMovieDate()));
        }else if (this.SORT_BY_MOVIETYPE == i){
            //类型的话可以按照拼音，这里选择使用哈希值来排序
            Collections.sort(movie,(x,y) -> x.hashCode() - y.hashCode());
        }else if (this.SORT_BY_MOVIECLICKRATE ==i){
            //点击率是整数，直接相减     降序
            Collections.sort(movie,(x,y) -> y.getMovieClickRate() - x.getMovieClickRate());
        }else if (this.SORT_BY_MOVIERECOMMENDRATE ==i){
            //同上   降序
            Collections.sort(movie,(x,y) -> y.getMovieRecommendRate() - x.getMovieRecommendRate());
        }else if ('6' == i){
            return null;
        }
        return movie;
    }

    /**
     * 通过下标得到movie对象
     * @param index
     * @return movie对象
     */
    public Movie getMovieByIndex(int index){
        return movies.get(--index);
    }

    /**
     * 得到列表中movie的长度
     * @return
     */
    public int getMovieSize(){
        return movies.size();
    }


    /**
     * 多条件查询
     * @param movieName
     * @param movieType
     * @param moviePerformer
     * @return
     */
    public List<Movie> selectMovieByParam(String movieName, String movieType, String moviePerformer) {
        // 模糊查询
        // 1、判断一个字符是否包含另一个字符   2、判断条件是否为空
        ArrayList<Movie> movieList = new ArrayList<>();
        for (Movie m : movies){
            if ((null == movieName || m.getMovieName().contains(movieName))
                    && (null == movieType || m.getMovieType().contains(movieType))
                    && (null == moviePerformer || m.getMoviePerformer().contains(moviePerformer))){
                movieList.add(m);
            }
        }
        return movieList;
    }

    /**
     * 观看影片 并且点击率+1
     * @param movieList 在该影片集合内挑选影片
     * @param i 影片序号
     */
    public void watchMovie(List<Movie> movieList,int i){
        //点击率+1
        int movieClickRate = movieList.get(--i).getMovieClickRate();
        movieList.get(i).setMovieClickRate(movieClickRate+1);
        System.out.println("影片《"+movieList.get(i).getMovieName()+"》观看结束");
    }

    /**
     * 影片推荐
     * @param movieList
     * @param i
     */
    public void updateRecommendRate(List<Movie> movieList,int i){
        int movieRecommendRate = movieList.get(--i).getMovieRecommendRate();
        movieList.get(i).setMovieRecommendRate(movieRecommendRate+1);
        System.out.println("====================推荐成功=====================");
    }

}
