package com.example.monumental.di

import android.app.Application
import com.example.monumental.data.manager.AuthenticationManagerImpl
import com.example.monumental.data.manager.LocalUserManagerImpl
import com.example.monumental.data.repository.local.HabitRepositoryImpl
import com.example.monumental.domain.manager.AuthenticationManager
import com.example.monumental.domain.manager.LocalUserManager
import com.example.monumental.domain.repository.HabitRepository
import com.example.monumental.domain.usecases.app_entry.AppEntryUseCase
import com.example.monumental.domain.usecases.app_entry.ReadAppEntry
import com.example.monumental.domain.usecases.app_entry.SaveAppEntry
import com.example.monumental.domain.usecases.authentication.AuthenticationUseCases
import com.example.monumental.domain.usecases.authentication.CreateAccountUseCase
import com.example.monumental.domain.usecases.authentication.CreateAnonymousAccountUseCase
import com.example.monumental.domain.usecases.authentication.LinkAccountUseCase
import com.example.monumental.domain.usecases.authentication.SignInUseCase
import com.example.monumental.domain.usecases.authentication.ValidateUseCase
import com.example.monumental.domain.usecases.habits.AddHabit
import com.example.monumental.domain.usecases.habits.GetAllHabits
import com.example.monumental.domain.usecases.habits.HabitUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideAuthenticationManager(
        application: Application
    ) : AuthenticationManager = AuthenticationManagerImpl(application)

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(
        authenticationManager: AuthenticationManager,
    ) = AuthenticationUseCases(
        createAnonymousAccount = CreateAnonymousAccountUseCase(authenticationManager),
        signInWithEmailPassword = SignInUseCase(authenticationManager),
        linkAccount = LinkAccountUseCase(authenticationManager),
        createAccount = CreateAccountUseCase(authenticationManager),
        validate = ValidateUseCase(),
    )

//    @Provides
//    @Singleton
//    fun provideHabitDatabase(
//        application: Application,
//    ) : HabitDatabase {
//        return HabitDatabase.getDatabase(application)
//    }

//    @Provides
//    @Singleton
//    fun provideHabitDao(
//        habitDatabase: HabitDatabase
//    ): HabitDao = habitDatabase.habitDao()

    @Provides
    @Singleton
    fun provideFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideHabitRepository(
        firestore: FirebaseFirestore,
    ) : HabitRepository = HabitRepositoryImpl(
        firestore = firestore,
    )

    @Provides
    @Singleton
    fun provideHabitsUseCases(
        habitRepository: HabitRepository
    ) = HabitUseCases(
         getAllHabits = GetAllHabits(habitRepository),
         addHabit = AddHabit(habitRepository)
    )
}