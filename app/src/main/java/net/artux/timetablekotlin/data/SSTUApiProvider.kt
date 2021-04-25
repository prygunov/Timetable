package net.artux.timetablekotlin.data

object SSTUApiProvider{

    val api :SSTUApiService by lazy { provideApi() };

    private fun provideApi():SSTUApiService{
        return SSTUApiService.create()
    }

}