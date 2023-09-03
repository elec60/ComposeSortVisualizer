package hashem.mousavi.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hashem.mousavi.domain.usecase.SortUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideSortUseCase(): SortUseCase {
        return SortUseCase()
    }
}