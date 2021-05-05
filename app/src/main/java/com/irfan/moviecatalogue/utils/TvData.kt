package com.irfan.moviecatalogue.utils


import com.irfan.moviecatalogue.data.local.entity.Movie

object TvData {

    private val listMovieTitle = arrayListOf(
        "Arrow",
        "Doom Patrol",
        "Dragon Ball",
        "Fairy tail",
        "Family Guy",
        "The Flash",
        "Game of Thrones",
        "Gotham",
        "Grey\'s Anatomy",
        "Hanna"
    )

    private val listMovieReleaseDate = arrayListOf(
        "2012",
        "2019",
        "1988",
        "2009",
        "1999",
        "2014",
        "2011",
        "2014",
        "2005",
        "2019"
    )

    private val listMovieOverview = arrayListOf(
        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
        "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
        "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku\'s home. Together, they set off to find all seven and to grant her wish.",
        "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn\'t just any ordinary kid, he\'s a member of one of the world\'s most infamous mage guilds: Fairy Tail.",
        "Sick, twisted, politically incorrect and Freakin\' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he\'s not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
        "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion — and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won\'t be long before the world learns what Barry Allen has become…The Flash.",
        "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night\'s Watch, is all that stands between the realms of men and icy horrors beyond.",
        "Everyone knows the name Commissioner Gordon. He is one of the crime world\'s greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon\'s story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world\'s most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
        "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
    )

    private val listMovieDuration = arrayListOf(
        "42m",
        "49m",
        "25m",
        "25m",
        "22m",
        "44m",
        "1h",
        "43m",
        "43m",
        "50m"
    )

    private val listMovieScore = arrayListOf(
        66,
        76,
        81,
        78,
        70,
        77,
        84,
        75,
        82,
        75
    )

    private val listMoviePosterImage = arrayListOf(
        "@drawable/tv_poster_arrow",
        "@drawable/tv_poster_doom_patrol",
        "@drawable/tv_poster_dragon_ball",
        "@drawable/tv_poster_fairytail",
        "@drawable/tv_poster_family_guy",
        "@drawable/tv_poster_flash",
        "@drawable/tv_poster_god",
        "@drawable/tv_poster_gotham",
        "@drawable/tv_poster_grey_anatomy",
        "@drawable/tv_poster_hanna"
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
