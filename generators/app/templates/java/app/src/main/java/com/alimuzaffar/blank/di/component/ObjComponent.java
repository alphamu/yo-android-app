package <%= package %>.di.component;


import <%= package %>.di.scope.UserScope;
import dagger.Component;

@UserScope
@Component(dependencies = AppComponent.class)
public interface ObjComponent {
    void inject(Object obj);
}
