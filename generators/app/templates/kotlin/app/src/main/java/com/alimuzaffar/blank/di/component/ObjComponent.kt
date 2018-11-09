package <%= package %>.di.component


import <%= package %>.di.scope.UserScope
import dagger.Component

@UserScope
@Component(dependencies = arrayOf(AppComponent::class))
interface ObjComponent {
    fun inject(obj: Any)
}
