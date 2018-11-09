package <%= package %>.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import <%= package %>.di.scope.ViewModelScope;
import <%= package %>.ui.main.FactoryViewModel;
import <%= package %>.ui.main.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/***
 * This module helps inject ViewModel objects with dependencies.
 * The reason it's used is because we never call the constructor of a ViewModel directly.
 * Below we have to define a bind method with @ViewModelScope for each ViewModel we wish to Inject.
 * In order to use this, inject ViewModelProvider.Factory into your Fragment or Activity.
 * then create the new model as follows:
 * <pre>{@code
 * class MyActivity extends Activity {
 *     @Inject
 *     ViewModelProvider.Factory viewModelFactory;
 *     private MyViewModel viewModel;
 *
 *     @Override
 *     protected void onCreate(Bundle savedInstanceState) {
 *          ...
 *          viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel.class);
 *     }
 * }
 * }</pre>
 */
@Module
public abstract class ViewModelModule {

    // One of these needs to be defined for every ViewModel we wish to bind.
    @Binds
    @IntoMap
    @ViewModelScope(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    // No need to define any more of these.
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);


}
