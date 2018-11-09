package <%= package %>.di.module;

import <%= package %>.ui.main.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * In order to use this, make sure the Activity implements HasFragmentInjector or
 * HasSupportFragmentInjector.
 * <pre>{@code
 * class MyActivity extends AppCompatActivity implements HasFragmentInjector {
 *     @Inject
 *     DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
 *
 *     @Override
 *     protected void onCreate(Bundle savedInstanceState) {
 *      // Yes, call it before calling super.
 *      AndroidInjection.inject(this);
 *      super.onCreate(savedInstanceState)
 *      ...
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
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeMainActivityFragment();

}