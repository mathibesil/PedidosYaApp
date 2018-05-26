import com.prueba.besil.pedidosyaprueba.ui.home.HomeFragmentModule
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeFragment
import com.prueba.besil.pedidosyaprueba.ui.home.view.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HomeFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun provideMapFragmentFactory(): MapFragment
}