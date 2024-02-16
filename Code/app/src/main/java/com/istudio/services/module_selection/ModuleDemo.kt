package com.istudio.services.module_selection
sealed class ModuleDemo(val rout: String) {

    data object DemoSelection : ModuleDemo("DemoSelection")


    data object NormalServiceScreen : ModuleDemo("NormalDownloadServiceScreen")
    data object IntentServiceScreen : ModuleDemo("IntentServiceScreen")

}