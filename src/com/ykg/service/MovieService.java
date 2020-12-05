/**
 * User: 杨康广
 * Date: 2020/12/2
 * Time: 21:35
 * Class MovieService
 */

package com.ykg.service;

import com.ykg.model.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieService {
    //保存电影的集合
    private List<Movie> movies = new ArrayList<>();
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
