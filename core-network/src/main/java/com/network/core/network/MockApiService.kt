package com.network.core.network

import com.network.core.dto.movieResponse.Movie
import com.network.core.dto.movieResponse.MovieRail
import com.network.core.dto.movieResponse.MovieRailsResponse
import com.skydoves.sandwich.ApiResponse
import java.util.UUID

class MockApiService : ApiService {


    override suspend fun getMovies(): ApiResponse<com.network.core.dto.ApiResponse<MovieRailsResponse>> {
//        delay(1000) // Simulate network delay
        return ApiResponse.Success(
            com.network.core.dto.ApiResponse(
                data = MovieRailsResponse(
                    rails = listOf(
                        MovieRail(
                            railId = "4561",
                            title = "Featured Movie",
                            movies = listOf(
                                Movie(
                                    id = "8",
                                    title = "Desert Storm",
                                    description = "A military unit stranded in the Rajasthan desert fights for survival.",
                                    posterUrl = "https://picsum.photos/300/400?random=8",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=8",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                                    genre = "War Action",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.6
                                ),
                                Movie(
                                    id = "7",
                                    title = "The Quantum Paradox",
                                    description = "Scientists accidentally create a parallel universe and must prevent reality collapse.",
                                    posterUrl = "https://picsum.photos/300/400?random=7",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=7",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                                    genre = "Sci-Fi Thriller",
                                    duration = "2h 10min",
                                    year = "2024",
                                    rating = 8.9
                                ),
                                Movie(
                                    id = "3",
                                    title = "Mumbai Monsoon",
                                    description = "A romantic drama unfolding during the heavy monsoon season in Mumbai.",
                                    posterUrl = "https://picsum.photos/300/400?random=3",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=3",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                                    genre = "Romance Drama",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.1,
                                    isFeatured = true
                                ),
                                Movie(
                                    id = "35",
                                    title = "The Delivery Boy",
                                    description = "A food delivery boy accidentally gets involved in a major criminal conspiracy.",
                                    posterUrl = "https://picsum.photos/300/400?random=35",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=35",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                                    genre = "Comedy Thriller",
                                    duration = "1h 50min",
                                    year = "2024",
                                    rating = 7.6
                                ),
                            ),
                            railType = MovieRail.RaleType.FEATURED_MOVIE,
                        ),
                        MovieRail(
                            railId = "4562",
                            title = "Continue watching",
                            movies = listOf(
                                Movie(
                                    id = "3",
                                    title = "Mumbai Monsoon",
                                    description = "A romantic drama unfolding during the heavy monsoon season in Mumbai.",
                                    posterUrl = "https://picsum.photos/300/400?random=3",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=3",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                                    genre = "Romance Drama",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.1,
                                    isFeatured = true
                                ),
                                Movie(
                                    id = "7",
                                    title = "The Quantum Paradox",
                                    description = "Scientists accidentally create a parallel universe and must prevent reality collapse.",
                                    posterUrl = "https://picsum.photos/300/400?random=7",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=7",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                                    genre = "Sci-Fi Thriller",
                                    duration = "2h 10min",
                                    year = "2024",
                                    rating = 8.9
                                ),
                                Movie(
                                    id = "8",
                                    title = "Desert Storm",
                                    description = "A military unit stranded in the Rajasthan desert fights for survival.",
                                    posterUrl = "https://picsum.photos/300/400?random=8",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=8",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                                    genre = "War Action",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.6
                                ),
                                Movie(
                                    id = "9",
                                    title = "The Kashmir Files: Untold",
                                    description = "A documentary-style drama revealing hidden stories from Kashmir's history.",
                                    posterUrl = "https://picsum.photos/300/400?random=9",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=9",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                                    genre = "Historical Drama",
                                    duration = "2h 40min",
                                    year = "2024",
                                    rating = 9.1
                                ),
                                Movie(
                                    id = "10",
                                    title = "Tech Titans",
                                    description = "The rise and fall of India's biggest tech startup and its controversial founder.",
                                    posterUrl = "https://picsum.photos/300/400?random=10",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=10",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                                    genre = "Biographical Drama",
                                    duration = "2h 25min",
                                    year = "2024",
                                    rating = 8.4
                                ),
                                Movie(
                                    id = "11",
                                    title = "The Haunted Palace",
                                    description = "A family inherits a mysterious palace in Rajasthan with dark supernatural secrets.",
                                    posterUrl = "https://picsum.photos/300/400?random=11",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=11",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
                                    genre = "Supernatural Horror",
                                    duration = "1h 45min",
                                    year = "2024",
                                    rating = 7.2
                                )
                            ),
                            railType = MovieRail.RaleType.CONTINUE_WATCHING,
                        ),
                        MovieRail(
                            railId = "4563",
                            title = "Trending Now",
                            movies = listOf(
                                Movie(
                                    id = "12",
                                    title = "Cricket Champions",
                                    description = "The inspiring story of India's women's cricket team winning the World Cup.",
                                    posterUrl = "https://picsum.photos/300/400?random=12",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=12",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                                    genre = "Sports Drama",
                                    duration = "2h 15min",
                                    year = "2024",
                                    rating = 8.8
                                ),
                                Movie(
                                    id = "13",
                                    title = "The Spy Network",
                                    description = "An Indian intelligence agent uncovers a conspiracy threatening national security.",
                                    posterUrl = "https://picsum.photos/300/400?random=13",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=13",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                                    genre = "Espionage Thriller",
                                    duration = "2h 30min",
                                    year = "2024",
                                    rating = 8.6
                                ),
                                Movie(
                                    id = "17",
                                    title = "The Art of Healing",
                                    description = "A doctor discovers ancient Ayurvedic medicine can cure modern diseases.",
                                    posterUrl = "https://picsum.photos/300/400?random=17",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=17",
                                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                                    genre = "Medical Drama",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.2
                                ),
                                Movie(
                                    id = "19",
                                    title = "The Wedding Planner's Dilemma",
                                    description = "A wedding planner faces chaos when multiple ceremonies clash on the same day.",
                                    posterUrl = "https://picsum.photos/300/400?random=19",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=19",
                                    videoUrl = "https://filesamples.com/samples/video/mp4/SampleVideo_1280x720_2mb.mp4",
                                    genre = "Comedy Drama",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.6
                                ),
                                Movie(
                                    id = "20",
                                    title = "Mountain Rescue",
                                    description = "A rescue team battles extreme weather to save climbers trapped in the Himalayas.",
                                    posterUrl = "https://picsum.photos/300/400?random=20",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=20",
                                    videoUrl = "https://filesamples.com/samples/video/mp4/SampleVideo_1280x720_5mb.mp4",
                                    genre = "Adventure Thriller",
                                    duration = "2h 10min",
                                    year = "2024",
                                    rating = 8.3
                                ),
                                Movie(
                                    id = "21",
                                    title = "The Food Truck Revolution",
                                    description = "A chef quits his fancy restaurant to start a food truck and discovers true happiness.",
                                    posterUrl = "https://picsum.photos/300/400?random=21",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=21",
                                    videoUrl = "https://media.w3.org/2010/05/sintel/trailer_hd.mp4",
                                    genre = "Feel-Good Drama",
                                    duration = "1h 45min",
                                    year = "2024",
                                    rating = 7.9
                                ),
                                Movie(
                                    id = "22",
                                    title = "Code Red: Hospital",
                                    description = "Medical staff work tirelessly during a city-wide emergency in Mumbai.",
                                    posterUrl = "https://picsum.photos/300/400?random=22",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=22",
                                    videoUrl = "https://media.w3.org/2010/05/bunny/trailer.mp4",
                                    genre = "Medical Thriller",
                                    duration = "2h 25min",
                                    year = "2024",
                                    rating = 8.7
                                )
                            ),
                            railType = MovieRail.RaleType.STANDARD,
                        ),
                        MovieRail(
                            railId = "4564",
                            title = "Popular Movies",
                            movies = listOf(
                                Movie(
                                    id = "23",
                                    title = "The Time Traveler's Wife",
                                    description = "A sci-fi romance about a man who travels through time to be with his love.",
                                    posterUrl = "https://picsum.photos/300/400?random=23",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=23",
                                    videoUrl = "https://media.w3.org/2010/05/video/movie_300.mp4",
                                    genre = "Sci-Fi Romance",
                                    duration = "2h 15min",
                                    year = "2024",
                                    rating = 8.4
                                ),
                                Movie(
                                    id = "24",
                                    title = "The Fisherman's Tale",
                                    description = "An old fisherman's encounter with mysterious sea creatures off the Kerala coast.",
                                    posterUrl = "https://picsum.photos/300/400?random=24",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=24",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                                    genre = "Fantasy Drama",
                                    duration = "1h 35min",
                                    year = "2024",
                                    rating = 7.7
                                ),
                                Movie(
                                    id = "25",
                                    title = "Urban Jungle",
                                    description = "Wildlife photographers document rare animals adapting to city life in Delhi.",
                                    posterUrl = "https://picsum.photos/300/400?random=25",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=25",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                                    genre = "Documentary Drama",
                                    duration = "1h 50min",
                                    year = "2024",
                                    rating = 8.1
                                ),
                                Movie(
                                    id = "26",
                                    title = "The Chess Master",
                                    description = "A young prodigy from rural India competes in the World Chess Championship.",
                                    posterUrl = "https://picsum.photos/300/400?random=26",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=26",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                                    genre = "Sports Biography",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.6
                                ),
                                Movie(
                                    id = "27",
                                    title = "Midnight Express",
                                    description = "A thrilling train journey turns into a hostage situation across India.",
                                    posterUrl = "https://picsum.photos/300/400?random=27",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=27",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                                    genre = "Action Thriller",
                                    duration = "2h 20min",
                                    year = "2024",
                                    rating = 8.2
                                ),
                                Movie(
                                    id = "28",
                                    title = "The Dance Academy",
                                    description = "Students at a prestigious dance academy compete for a scholarship to Paris.",
                                    posterUrl = "https://picsum.photos/300/400?random=28",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=28",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
                                    genre = "Musical Drama",
                                    duration = "2h 10min",
                                    year = "2024",
                                    rating = 7.8
                                ),
                                Movie(
                                    id = "29",
                                    title = "The Startup Story",
                                    description = "Three friends build a revolutionary app that changes Indian commerce forever.",
                                    posterUrl = "https://picsum.photos/300/400?random=29",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=29",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                                    genre = "Business Drama",
                                    duration = "2h 30min",
                                    year = "2024",
                                    rating = 8.5
                                ),
                                Movie(
                                    id = "30",
                                    title = "The Night Shift",
                                    description = "Hospital workers face supernatural encounters during the graveyard shift.",
                                    posterUrl = "https://picsum.photos/300/400?random=30",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=30",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                                    genre = "Horror Thriller",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.3
                                ),
                                Movie(
                                    id = "31",
                                    title = "Border Patrol",
                                    description = "Indian border security forces protect the nation against infiltration attempts.",
                                    posterUrl = "https://picsum.photos/300/400?random=31",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=31",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                                    genre = "Military Action",
                                    duration = "2h 15min",
                                    year = "2024",
                                    rating = 8.4
                                ),
                                Movie(
                                    id = "32",
                                    title = "The Fashion Designer",
                                    description = "A small-town tailor becomes an international fashion icon against all odds.",
                                    posterUrl = "https://picsum.photos/300/400?random=32",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=32",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                                    genre = "Inspirational Drama",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.0
                                ),
                                Movie(
                                    id = "33",
                                    title = "The Virtual Reality Trap",
                                    description = "Gamers become trapped in a virtual reality world and must fight to escape.",
                                    posterUrl = "https://picsum.photos/300/400?random=33",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=33",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                                    genre = "Sci-Fi Action",
                                    duration = "2h 25min",
                                    year = "2024",
                                    rating = 8.1
                                ),
                                Movie(
                                    id = "34",
                                    title = "The Street Photographer",
                                    description = "A photographer documents the changing face of Old Delhi through his lens.",
                                    posterUrl = "https://picsum.photos/300/400?random=34",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=34",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
                                    genre = "Artistic Drama",
                                    duration = "1h 45min",
                                    year = "2024",
                                    rating = 7.9
                                ),
                                Movie(
                                    id = "35",
                                    title = "The Delivery Boy",
                                    description = "A food delivery boy accidentally gets involved in a major criminal conspiracy.",
                                    posterUrl = "https://picsum.photos/300/400?random=35",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=35",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                                    genre = "Comedy Thriller",
                                    duration = "1h 50min",
                                    year = "2024",
                                    rating = 7.6
                                ),
                                Movie(
                                    id = "36",
                                    title = "The School Teacher",
                                    description = "A dedicated teacher transforms a failing rural school into an educational success.",
                                    posterUrl = "https://picsum.photos/300/400?random=36",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=36",
                                    videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                                    genre = "Inspirational Drama",
                                    duration = "2h 10min",
                                    year = "2024",
                                    rating = 8.7
                                ),
                                Movie(
                                    id = "37",
                                    title = "The Rickshaw Driver's Dream",
                                    description = "An auto-rickshaw driver's journey from the streets to becoming a successful entrepreneur.",
                                    posterUrl = "https://picsum.photos/300/400?random=37",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=37",
                                    videoUrl = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4",
                                    genre = "Biographical Drama",
                                    duration = "2h 20min",
                                    year = "2024",
                                    rating = 8.3
                                ),
                                Movie(
                                    id = "38",
                                    title = "The Ghost Ship",
                                    description = "A cargo ship crew encounters supernatural forces in the Arabian Sea.",
                                    posterUrl = "https://picsum.photos/300/400?random=38",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=38",
                                    videoUrl = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/720/Big_Buck_Bunny_720_10s_1MB.mp4",
                                    genre = "Supernatural Thriller",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.4
                                ),
                                Movie(
                                    id = "39",
                                    title = "The Yoga Master",
                                    description = "An ancient yoga guru shares his wisdom with modern spiritual seekers.",
                                    posterUrl = "https://picsum.photos/300/400?random=39",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=39",
                                    videoUrl = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_1MB.mp4",
                                    genre = "Spiritual Drama",
                                    duration = "1h 40min",
                                    year = "2024",
                                    rating = 8.1
                                ),
                                Movie(
                                    id = "40",
                                    title = "The Racing Legend",
                                    description = "A Formula 1 driver from India competes for the world championship.",
                                    posterUrl = "https://picsum.photos/300/400?random=40",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=40",
                                    videoUrl = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8",
                                    genre = "Sports Action",
                                    duration = "2h 15min",
                                    year = "2024",
                                    rating = 8.5
                                ),
                                Movie(
                                    id = "41",
                                    title = "The Social Media Influencer",
                                    description = "A teenager's rise to fame on social media leads to unexpected consequences.",
                                    posterUrl = "https://picsum.photos/300/400?random=41",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=41",
                                    videoUrl = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
                                    genre = "Contemporary Drama",
                                    duration = "1h 48min",
                                    year = "2024",
                                    rating = 7.7
                                )
                            ),
                            railType = MovieRail.RaleType.LARGE,
                        ),
                        MovieRail(
                            railId = "4565",
                            title = "Recently Added",
                            movies = listOf(
                                Movie(
                                    id = "42",
                                    title = "The Mountain Climber",
                                    description = "A disabled mountaineer attempts to climb Mount Everest against all odds.",
                                    posterUrl = "https://picsum.photos/300/400?random=42",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=42",
                                    videoUrl = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8",
                                    genre = "Inspirational Adventure",
                                    duration = "2h 30min",
                                    year = "2024",
                                    rating = 9.0
                                ),
                                Movie(
                                    id = "43",
                                    title = "The Stand-Up Comedian",
                                    description = "A struggling comedian finds his voice while dealing with personal tragedies.",
                                    posterUrl = "https://picsum.photos/300/400?random=43",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=43",
                                    videoUrl = "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8",
                                    genre = "Comedy Drama",
                                    duration = "1h 52min",
                                    year = "2024",
                                    rating = 8.2
                                ),
                                Movie(
                                    id = "44",
                                    title = "The Lost City",
                                    description = "Archaeologists discover an ancient lost city beneath modern Mumbai.",
                                    posterUrl = "https://picsum.photos/300/400?random=44",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=44",
                                    videoUrl = "https://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8",
                                    genre = "Adventure Mystery",
                                    duration = "2h 25min",
                                    year = "2024",
                                    rating = 8.4
                                ),
                                Movie(
                                    id = "45",
                                    title = "The AI Revolution",
                                    description = "Scientists create an AI that becomes self-aware and questions human authority.",
                                    posterUrl = "https://picsum.photos/300/400?random=45",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=45",
                                    videoUrl = "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",
                                    genre = "Sci-Fi Thriller",
                                    duration = "2h 35min",
                                    year = "2024",
                                    rating = 8.8
                                ),
                                Movie(
                                    id = "46",
                                    title = "The Village Doctor",
                                    description = "A city doctor moves to a remote village and discovers the power of traditional medicine.",
                                    posterUrl = "https://picsum.photos/300/400?random=46",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=46",
                                    videoUrl = "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_adv_example_hevc/master.m3u8",
                                    genre = "Medical Drama",
                                    duration = "2h 8min",
                                    year = "2024",
                                    rating = 8.0
                                ),
                                Movie(
                                    id = "47",
                                    title = "The Street Musician",
                                    description = "A talented street musician gets discovered and faces the challenges of fame.",
                                    posterUrl = "https://picsum.photos/300/400?random=47",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=47",
                                    videoUrl = "https://devstreaming-cdn.apple.com/videos/streaming/examples/adv_dv_atmos/main.m3u8",
                                    genre = "Musical Biography",
                                    duration = "2h 12min",
                                    year = "2024",
                                    rating = 8.3
                                ),
                                Movie(
                                    id = "48",
                                    title = "The Cyber Cafe Owner",
                                    description = "A cyber cafe owner uncovers a massive online fraud network.",
                                    posterUrl = "https://picsum.photos/300/400?random=48",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=48",
                                    videoUrl = "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd",
                                    genre = "Crime Thriller",
                                    duration = "1h 58min",
                                    year = "2024",
                                    rating = 7.8
                                ),
                                Movie(
                                    id = "49",
                                    title = "The Monsoon Wedding",
                                    description = "A big fat Indian wedding faces chaos when the monsoon arrives early.",
                                    posterUrl = "https://picsum.photos/300/400?random=49",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=49",
                                    videoUrl = "https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
                                    genre = "Wedding Comedy",
                                    duration = "2h 18min",
                                    year = "2024",
                                    rating = 7.9
                                ),
                                Movie(
                                    id = "50",
                                    title = "The Night Watchman",
                                    description = "A security guard at a museum discovers that the exhibits come alive at night.",
                                    posterUrl = "https://picsum.photos/300/400?random=50",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=50",
                                    videoUrl = "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8",
                                    genre = "Fantasy Comedy",
                                    duration = "1h 42min",
                                    year = "2024",
                                    rating = 7.5
                                ),
                                Movie(
                                    id = "51",
                                    title = "The Electric Vehicle Pioneer",
                                    description = "An engineer's journey to revolutionize transportation in India with electric vehicles.",
                                    posterUrl = "https://picsum.photos/300/400?random=51",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=51",
                                    videoUrl = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.mp4",
                                    genre = "Biographical Drama",
                                    duration = "2h 22min",
                                    year = "2024",
                                    rating = 8.6
                                ),
                                Movie(
                                    id = "52",
                                    title = "The Detective's Last Case",
                                    description = "A veteran detective solves one final mystery before retirement in the streets of Kolkata.",
                                    posterUrl = "https://picsum.photos/300/400?random=52",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=52",
                                    videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_720x480_1mb.mp4",
                                    genre = "Crime Mystery",
                                    duration = "2h 5min",
                                    year = "2024",
                                    rating = 8.7
                                ),
                                Movie(
                                    id = "53",
                                    title = "The Organic Farmer",
                                    description = "A corporate executive quits his job to become an organic farmer in rural Punjab.",
                                    posterUrl = "https://picsum.photos/300/400?random=53",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=53",
                                    videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_720x480_2mb.mp4",
                                    genre = "Inspirational Drama",
                                    duration = "2h 15min",
                                    year = "2024",
                                    rating = 8.1
                                ),
                                Movie(
                                    id = "54",
                                    title = "The Digital Nomad",
                                    description = "A software developer travels across India while working remotely and finding himself.",
                                    posterUrl = "https://picsum.photos/300/400?random=54",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=54",
                                    videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_720x480_5mb.mp4",
                                    genre = "Contemporary Drama",
                                    duration = "1h 55min",
                                    year = "2024",
                                    rating = 7.8
                                ),
                                Movie(
                                    id = "55",
                                    title = "The Underwater Explorer",
                                    description = "Marine biologists discover a new species in the depths of the Indian Ocean.",
                                    posterUrl = "https://picsum.photos/300/400?random=55",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=55",
                                    videoUrl = "https://www.html5rocks.com/en/tutorials/video/basics/devstories.mp4",
                                    genre = "Adventure Documentary",
                                    duration = "1h 48min",
                                    year = "2024",
                                    rating = 8.3
                                ),
                                Movie(
                                    id = "56",
                                    title = "The Solar Energy Crusader",
                                    description = "An activist fights to bring solar power to remote villages across India.",
                                    posterUrl = "https://picsum.photos/300/400?random=56",
                                    backdropUrl = "https://picsum.photos/1920/1080?random=56",
                                    videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                                    genre = "Environmental Drama",
                                    duration = "2h 28min",
                                    year = "2024",
                                    rating = 8.5
                                )
                            ),
                            railType = MovieRail.RaleType.STANDARD,
                        )

                    )
                ),
                is_success = true,
                status_code = 200,
                error = null,
                meta = null
            )
        )
    }
}