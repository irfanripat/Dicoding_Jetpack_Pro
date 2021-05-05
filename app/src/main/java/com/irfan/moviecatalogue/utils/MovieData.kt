package com.irfan.moviecatalogue.utils


import com.irfan.moviecatalogue.data.local.entity.Movie


object MovieData {

    private val listMovieTitle = arrayListOf(
        "A Star Is Born",
        "Alita: Battle Angel",
        "Aquaman",
        "Bohemian Rhapsody",
        "Cold Pursuit",
        "Creed II",
        "Fantastic Beasts: The Crimes of Grindelwald",
        "Glass",
        "How to Train Your Dragon: The Hidden World",
        "Avengers: Infinity War"
    )

    private val listMovieReleaseDate = arrayListOf(
        "10/05/2018",
        "2/14/2019",
        "2/21/2018",
        "1/02/2018",
        "2/08/2019",
        "1/21/2018",
        "1/16/2018",
        "1/18/2019",
        "2/22/2019",
        "4/27/2018"
    )

    private val listMovieOverview = arrayListOf(
        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\'s half-human, half-Atlantean brother and true heir to the throne.",
        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \'n\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
        "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son\'s murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking\'s associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
        "Between personal obligations and training for his next big fight against an opponent with ties to his family\'s past, Adonis Creed is up against the challenge of his life.",
        "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
    )

    private val listMovieDuration = arrayListOf(
        "2h 16m",
        "2h 2m",
        "2h 23m",
        "2h 15m",
        "1h 59m",
        "2h 10m",
        "2h 14m",
        "2h 9m",
        "1h 44m",
        "2h 29m"
    )

    private val listMovieScore = arrayListOf(
        75,
        72,
        69,
        80,
        57,
        69,
        69,
        67,
        78,
        83
    )

    private val listMoviePosterImage = arrayListOf(
        "@drawable/movie_poster_a_start_is_born",
        "@drawable/movie_poster_alita",
        "@drawable/movie_poster_aquaman",
        "@drawable/movie_poster_bohemian",
        "@drawable/movie_poster_cold_persuit",
        "@drawable/movie_poster_creed",
        "@drawable/movie_poster_crimes",
        "@drawable/movie_poster_glass",
        "@drawable/movie_poster_how_to_train",
        "@drawable/movie_poster_infinity_war"
    )

    val listData: ArrayList<Movie>
        get() {
            val list = arrayListOf<Movie>()
            for (position in listMovieTitle.indices) {
                val movie = Movie(
                    listMovieTitle[position],
                    listMovieReleaseDate[position],
                    listMovieOverview[position],
                    listMovieDuration[position],
                    listMovieScore[position],
                    listMoviePosterImage[position]
                )
                list.add(movie)
            }
            return list
        }
}
