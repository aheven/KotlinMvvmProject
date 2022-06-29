package heven.holt.mvvm.repository

import heven.holt.mvvm.model.TrackVideo

object DataRepository {
    private val zxcVideoList: List<TrackVideo> = mutableListOf<TrackVideo>().apply {
        add(TrackVideo("101", "功夫", "1", "周星驰主演电影"))
        add(TrackVideo("102", "少林足球", "1", "周星驰主演电影"))
        add(TrackVideo("103", "大话西游", "1", "周星驰主演电影"))
        add(TrackVideo("104", "大话西游2", "1", "周星驰主演电影"))
        add(TrackVideo("105", "逃学威龙", "1", "周星驰主演电影"))
        add(TrackVideo("106", "逃学威龙2", "1", "周星驰主演电影"))
        add(TrackVideo("107", "逃学威龙3", "1", "周星驰主演电影"))
    }
    private val marvelVideoList: List<TrackVideo> = mutableListOf<TrackVideo>().apply {
        add(TrackVideo("111", "钢铁侠", "2", "漫威系列电影"))
        add(TrackVideo("112", "钢铁侠2", "2", "漫威系列电影"))
        add(TrackVideo("113", "钢铁侠3", "2", "漫威系列电影"))
        add(TrackVideo("114", "美国队长", "2", "漫威系列电影"))
        add(TrackVideo("115", "美国队长2", "2", "漫威系列电影"))
        add(TrackVideo("116", "美国队长3内战", "2", "漫威系列电影"))
        add(TrackVideo("117", "复仇者联盟", "2", "漫威系列电影"))
        add(TrackVideo("118", "复仇者联盟2", "2", "漫威系列电影"))
        add(TrackVideo("119", "复仇者联盟3", "2", "漫威系列电影"))
        add(TrackVideo("120", "复仇者联盟4", "2", "漫威系列电影"))
    }


    fun getRecommendVideos(): List<TrackVideo> {
        val list = ArrayList<TrackVideo>()
        list.add(TrackVideo("100", "罗小黑战纪大电影", "3"))
        list.add(TrackVideo("101", "功夫", "1", "周星驰主演电影"))
        list.add(TrackVideo("102", "少林足球", "1", "周星驰主演电影"))
        list.add(TrackVideo("111", "钢铁侠", "2", "漫威系列电影"))
        return list
    }

    fun getMovieVideos(): List<TrackVideo> {
        val list: java.util.ArrayList<TrackVideo> = java.util.ArrayList<TrackVideo>()
        list.add(TrackVideo("111", "钢铁侠", "2", "漫威系列电影"))
        list.add(TrackVideo("112", "钢铁侠2", "2", "漫威系列电影"))
        list.add(TrackVideo("113", "钢铁侠3", "2", "漫威系列电影"))
        list.add(TrackVideo("105", "逃学威龙", "1", "周星驰主演电影"))
        list.add(TrackVideo("106", "逃学威龙2", "1", "周星驰主演电影"))
        list.add(TrackVideo("107", "逃学威龙3", "1", "周星驰主演电影"))
        return list
    }

    fun getSeriesMovies(id: String?): List<TrackVideo?> {
        for (video in zxcVideoList) {
            if (video.id == id) {
                return zxcVideoList
            }
        }
        for (video in marvelVideoList) {
            if (video.id == id) {
                return marvelVideoList
            }
        }
        return emptyList()
    }
}