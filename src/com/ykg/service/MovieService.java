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
        movies.add(new Movie(123455,"qq","qw","qwe","张三",new Date(),0,0));
        movies.add(new Movie(123456,"ww","qw","qwe","李四",new Date(),0,0));
        movies.add(new Movie(123457,"tt","qw","qwe","王五",new Date(),0,0));
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
     * 影片按照指定属性排序（默认自然升序）
     * @param c 指定的属性的序号
     * @param movie 需要排序的影片集合
     */
    public void sortMovies(char c,List<Movie> movie){
        // char类型转为int类型（int = char - 48）
        int i = c - 48;
        if (this.SORT_BY_MOVIENAME == i){
            //使用lambda表达式，使用Collections.sort方法进行排序
                                 //按照名称的拼音，所以需要使用Collator指定中国的语言   传入两个比较的字符串
            Collections.sort(movie,(x,y) -> Collator.getInstance(Locale.CHINA).compare(x.getMovieName(),y.getMovieName()));

        }else if (this.SORT_BY_MOVIEDATE == i){
            //日期里面有compareTo方法，直接调用
            Collections.sort(movie,(x,y) -> x.getMovieDate().compareTo(y.getMovieDate()));
        }else if (this.SORT_BY_MOVIETYPE == i){
            //类型的话可以按照拼音，这里选择使用哈希值来排序
            Collections.sort(movie,(x,y) -> x.hashCode() - y.hashCode());
        }else if (this.SORT_BY_MOVIECLICKRATE ==i){
            //点击率是整数，直接相减
            Collections.sort(movie,(x,y) -> x.getMovieClickRate() - y.getMovieClickRate());
        }else if (this.SORT_BY_MOVIERECOMMENDRATE ==i){
            //同上
            Collections.sort(movie,(x,y) -> x.getMovieRecommendRate() - y.getMovieRecommendRate());
        }
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
}
