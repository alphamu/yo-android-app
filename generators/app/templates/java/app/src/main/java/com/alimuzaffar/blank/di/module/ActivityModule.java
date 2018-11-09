package <%= package %>.di.module;

import <%= package %>.ui.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Each activity you wish to inject needs to have an abstract contribute methods below.
 * <pre>{@code
 * class MyActivity extends Activity implements HasFragmentInjector {
 *     @Inject
 *     DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
 *
 *     @Override
 *     protected void onCreate(Bundle savedInstanceState) {
 *      // Yes, call it before calling super.
 *      AndroidInjection.inject(this);
 *      super.onCreate(savedInstanceState)
 *      ...
 *
 *     }
 *
 *     @Override
 *     public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
 *         return dispatchingAndroidInjector;
 *     }
 * }
 *
 * class MyFragment extends Fragment {
 *     ...
 *     @Override
 *     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
 *         AndroidInjection.inject(this);
 *         // If using appcompat Fragment
 *         // AndroidSupportInjection.inject(this);
 *         super.onActivityCreated(savedInstanceState);
 *     }
 *     ...
 * }
 * }</pre>
 */
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

}