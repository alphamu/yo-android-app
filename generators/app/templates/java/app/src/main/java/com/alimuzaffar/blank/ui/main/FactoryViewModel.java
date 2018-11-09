package <%= package %>.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

/***
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
@Singleton
public class FactoryViewModel implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public FactoryViewModel(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
