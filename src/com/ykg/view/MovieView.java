/**
 * User: 杨
 * Date: 2020/01/01
 * Time: 11:00
 * Class MovieView
 */

package com.ykg.view;

import com.ykg.model.Movie;
import com.ykg.service.MovieService;
import com.ykg.util.RandomUtil;
import com.ykg.util.ScannerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MovieView {

    MovieService ms = new MovieService();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++影院系统++++++++++++++++++++++++++++++++");
            System.out.println("1、影片管理\t\t\t2、查看所有影片\t\t\t3、退出系统");
            System.out.print("请选择序号：");
            char c = ScannerUtil.readMenuSelect(3);
            choose1_first(c);
        }
    }

    /**
     * 一级选择
     * @param choose
     */
    public void choose1_first(char choose){
        switch (choose){
            //1、影片管理
            case '1':
                System.out.println("1、上传影片\t2、删除影片\t3、修改影片\t4、返回上一级");
                System.out.print("选择序号：");
                char c1 = ScannerUtil.readMenuSelect(4);
                // 二级选择
                choose11_manageMovie(c1);
//                System.out.println(c);
                break;
            //2、查看所有影片
            case '2':
//                System.out.println("---查看所有影片--- ");
                showMovies(ms.getMovies());
                if (ms.getMovieSize()==0){
                    return;
                }
                System.out.println("1、按条件搜索\t2、影片排序\t3、返回上一级");
                System.out.print("选择序号：");
                char c2 = ScannerUtil.readMenuSelect(3);
                // 二级选择
                choose12_selelctMovie(c2);
                break;
            //3、退出系统
            default:
                System.exit(0);
                break;
        }
    }


    /**
     * 影片管理
     * @param choose
     */
    private void choose11_manageMovie(char choose){
        switch (choose){
            case '1':
                System.out.println("---------------------------上传影片------------------------------------------");
                System.out.println("输入影片相关属性");
                Movie movie = addMovieParam();
                ms.addMovie(movie);
                System.out.println("================================上传成功======================================");
                break;
            case '2':
                System.out.println("-----------------删除影片----------------(返回第一菜单界面请输入“0”)-------------------");
                showMovies(ms.getMovies());
                if (ms.getMovieSize()==0){
                    return;
                }
                System.out.println("请输入需要删除的电影的序号（INDEX）:");
                int index = ScannerUtil.readInt(3);
                if (index > 0 && index<=ms.getMovieSize()){
                    ms.removeMovie(index);
                    System.out.println("================================删除成功======================================");
                    break;
                }else if (index>ms.getMovieSize()){
                    System.out.println("您输入的序号"+index+"超出范围，正在返回第一菜单界面");
                    break;
                }
                break;
            case '3':
                System.out.println("----------------------------修改影片----------(返回第一菜单界面请输入“0”)----------------");
                showMovies(ms.getMovies());
                if (ms.getMovieSize()==0){
                    return;
                }
                System.out.println("请输入需要修改的电影的序号（INDEX）:");
                int index1 = ScannerUtil.readInt(3);
                if (index1>ms.getMovieSize()){
                    System.out.println("您输入的序号"+index1+"超出范围，正在第一菜单界面");
                    break;
                }else if (index1==0){
                    break;
                }
                Movie movieByIndex = ms.getMovieByIndex(index1);
//                System.out.println(movieByIndex);
                Movie movie1 = updateMovieParam(movieByIndex);
                ms.updateMovie(index1,movie1);
                System.out.println("输入影片需要修改相关属性");

                System.out.println("================================修改成功======================================");
                break;
            default:
                break;
        }
    }

    /**
     * 查看所有影片
     */
    private void choose12_selelctMovie(char choose){
        switch (choose){
            //1、按条件搜索
            case '1':
                //按条件模糊查询影片
                List<Movie> movieList = fuzzyQueryMovies();
                //如果查询后返回集合长度为0，则返回上一级
                if (movieList.size() == 0){
                    break;
                }
                while (true){
                    System.out.println("1、影片排序\t2、观看影片\t3、推荐影片\t4、返回影院系统\t5、退出系统");
                    char c1 = ScannerUtil.readMenuSelect(5);
                    choose121(c1,movieList);
                    if ('4' == c1){
                        break;
                    }
                }
                break;
            //2、影片排序
            case '2':
                choose121('1',ms.getMovies());
                while (true){
                    System.out.println("1、观看影片\t2、推荐影片\t3、返回影院系统\t4、退出系统");
                    int i2 = ScannerUtil.readMenuSelect(4) - 48;
                    char c2 = (char)(i2+49);
                    choose121(c2,ms.getMovies());
                    if ('4' == c2){
                        break;
                    }
                }
                break;
            //3、返回上一级
            default:
                break;
        }
    }


    /**
     * 查看所有影片之后的三级选择
     * @param choose
     * @param movieList 排序完成之后的影片集合
     *
     */
    private void choose121(char choose,List<Movie> movieList){
        switch (choose){
            // 1、条件搜索后影片排序
            case '1':
                System.out.println("请输入排序条件:");
                System.out.println("1、按名称\t2、按上映日期\t3、按类型\t4、按点击率\t5、按推荐率\t6、返回上一级");
                char c1 = ScannerUtil.readMenuSelect(6);
                movieList = ms.sortMovies(c1, movieList);
                if (null == movieList){
                    break;
                }
                showMovies(movieList);
                break;
            // 2、观看影片
            case '2':
                System.out.print("(输入0退出观看)请输入影片序号：");
                int i = ScannerUtil.readCount(movieList.size());
                if (0 == i){
                    break;
                }
                ms.watchMovie(movieList,i);
                System.out.print("是否推荐此影片？（Y/N）：");
                char c2 = ScannerUtil.readYN();
                if ('Y' == c2){
                    ms.updateRecommendRate(movieList,i);
                    showMovies(movieList);
                    break;
                }
                showMovies(movieList);
                break;
                // 3、推荐影片
            case '3':
                System.out.print("请选择需要推荐的电影序号(INDEX)：");
                int index = ScannerUtil.readCount(movieList.size());
                System.out.print("确认推荐（Y/N）：");
                char c3 = ScannerUtil.readYN();
                if ('Y' == c3){
                    ms.updateRecommendRate(movieList,index);
                    showMovies(movieList);
                    break;
                }
                showMovies(movieList);
                break;
            // 4、返回影院系统
            case '4':
                break;
            // 5、退出系统
            default:
                System.exit(0);
        }
    }

    /**
     * 影片的模糊查询、多条件查询
     * @return 返回排序后的影片集合
     */
    private List<Movie> fuzzyQueryMovies(){
        System.out.println("请输入查询条件:");
        System.out.print("影片名称：");
        String movieName = ScannerUtil.readString(5,null);

        System.out.print("影片类型：");
        String movieType = ScannerUtil.readString(5,null);

        System.out.print("影片主演：");
        String moviePerformer = ScannerUtil.readString(5,null);

        if (null ==movieName && null == movieType && null ==moviePerformer){
            System.out.println("您的查询条件是: 无");
        }
        System.out.println("1、搜索\t2、返回上一级");
        System.out.print("请选择序号:");
        char c = ScannerUtil.readMenuSelect(2);
        List<Movie> moviesList = new ArrayList<>();
        if ('1' == c){
            moviesList = ms.selectMovieByParam(movieName, movieType, moviePerformer);
            showMovies(moviesList);
            return moviesList;
        }
        return moviesList;
    }

    /**
     * 控制台获取用户输入的电影属性构建电影对象
     * @return Movie
     */
    private Movie addMovieParam(){
        System.out.print("影片名称：");
        String movieName = ScannerUtil.readString(8);

        System.out.print("影片类型：");
        String movieType = ScannerUtil.readString(8);

        System.out.print("影片主演：");
        String moviePerformer = ScannerUtil.readString(8);

        System.out.print("影片导演：");
        String movieGuide = ScannerUtil.readString(8);

        System.out.print("影片上映时间（2020-01-01）：");
        Date movieDate = ScannerUtil.readDate("yyyy-MM-dd");


        return new Movie(RandomUtil.getRandomInt(),movieName,movieType,moviePerformer,movieGuide,movieDate,0,0);
    }

    /**
     * 修改影片
     * @param movie
     * @return
     */
    private Movie updateMovieParam(Movie movie){
//        System.out.println(movie.getMovieID());

        System.out.print("影片名称("+movie.getMovieName()+")：");
        String movieName = ScannerUtil.readString(8,movie.getMovieName());

        System.out.print("影片类型("+movie.getMovieType()+")：");
        String movieType = ScannerUtil.readString(8,movie.getMovieType());

        System.out.print("影片主演("+movie.getMoviePerformer()+")：");
        String moviePerformer = ScannerUtil.readString(8,movie.getMoviePerformer());

        System.out.print("影片导演("+movie.getMovieGuider()+")：");
        String movieGuide = ScannerUtil.readString(8,movie.getMovieGuider());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("影片上映时间（"+sdf.format(movie.getMovieDate())+"）：");
        Date movieDate = ScannerUtil.readDate("yyyy-MM-dd",movie.getMovieDate());

        return new Movie(movie.getMovieID(),movieName,movieType,moviePerformer,movieGuide,movieDate,movie.getMovieClickRate(),movie.getMovieRecommendRate());
    }


    /**
     * 遍历影片
     */
    public void showMovies(List<Movie> movies){
        int index = 1;
        System.out.println("INDEX/ID\t电影名称\t\t\t电影类型\t\t电影主演\t\t电影导演\t\t上映日期\t\t\t点击率\t推荐率");

        if (movies.size() ==0){
            System.out.println();
            System.out.println("_____________________________!!!!!!!暂无数据!!!!!!_____________________________");
            return;
        }

        for (Movie movie :movies){
            System.out.println(index+"/"+movie);
            index++;
        }
    }
}
