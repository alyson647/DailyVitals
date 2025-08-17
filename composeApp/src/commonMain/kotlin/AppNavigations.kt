internal enum class Screen {
    FirstOnboarding,
    SecondOnboarding,
    ThirdOnboarding,
    Home,
    History,
    Notifications
}

internal sealed class AppDirections {
    data object Back : AppDirections()
    data object Next : AppDirections()
    data object Home : AppDirections()
}
