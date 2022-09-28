package com.example.publicizeeventsapp.di
import com.example.publicizeeventsapp.data.datasource.remote.EventsRemoteDataSourceImpl
import com.example.publicizeeventsapp.data.mapper.EventsMapper
import com.example.publicizeeventsapp.data.repository.EventsRepositoryImpl
import com.example.publicizeeventsapp.data.retrofit.ServiceProvider
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import com.example.publicizeeventsapp.domain.usecase.GetEventsUseCase
import com.example.publicizeeventsapp.presentation.viewmodel.EventsViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModules = module {
    single<ServiceProvider> {
        ServiceProvider()
    }
}

val dataModules = module {
    factory <EventsRepository>{
        EventsRepositoryImpl(
            remoteDataSource = EventsRemoteDataSourceImpl(
                serviceProvider = get()
            ),
            eventsMapper = EventsMapper()
        )
    }
}

val domainModules = module {
    factory { GetEventsUseCase(repository = get()) }
}

val presentationModules = module {
    viewModel {
        EventsViewModel(
            getEventsUseCase = get()
        )
    }
}

