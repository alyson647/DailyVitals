internal enum class Screen {
    FirstOnboarding,
    SecondOnboarding,
    ThirdOnboarding,
    Home
}

internal sealed class OnboardingDirections {
    data object Back : OnboardingDirections()
    data object Next : OnboardingDirections()
    data object Home : OnboardingDirections()
}
