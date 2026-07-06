rootProject.name = "dawn-server-api"

includeBuild("build-logic")

include(
    "api",
    "common",
    "messaging",
    "bukkit",
    //"bungee",
    //"velocity",
    "examples:shop",
    "examples:shop-html",
)