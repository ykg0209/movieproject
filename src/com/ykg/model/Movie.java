/**
 * User: 杨
 * Date: 2020/01/01
 * Time: 10:42
 * Class Movie
 */

package com.ykg.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    private int movieID;
    private String movieName;
    private String movieType;
    private String moviePerformer;  //主演
    private String movieGuider;     //导演
    private Date movieDate;         //上映时间
    private int movieClickRate;    //点击率
    private int movieRecommendRate;   //推荐率


    public Movie() {
    }

    public Movie(int movieID, String movieName, String movieType, String moviePerformer, String movieGuider, Date movieDate, int movieClickRate, int movieRecommendRate) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieType = movieType;
        this.moviePerformer = moviePerformer;
        this.movieGuider = movieGuider;
        this.movieDate = movieDate;
        this.movieClickRate = movieClickRate;
        this.movieRecommendRate = movieRecommendRate;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMoviePerformer() {
        return moviePerformer;
    }

    public void setMoviePerformer(String moviePerformer) {
        this.moviePerformer = moviePerformer;
    }

    public String getMovieGuider() {
        return movieGuider;
    }

    public void setMovieGuider(String movieGuider) {
        this.movieGuider = movieGuider;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }

    public int getMovieClickRate() {
        return movieClickRate;
    }

    public void setMovieClickRate(int movieClickRate) {
        this.movieClickRate = movieClickRate;
    }

    public int getMovieRecommendRate() {
        return movieRecommendRate;
    }

    public void setMovieRecommendRate(int movieRecommendRate) {
        this.movieRecommendRate = movieRecommendRate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return movieID +
                "\t" + movieName +
                "\t\t" + movieType +
                "\t\t" + moviePerformer +
                "\t\t" + movieGuider +
                "\t\t" + sdf.format(movieDate) +
                "\t\t" + movieClickRate +
                "\t\t" + movieRecommendRate;
    }
}
