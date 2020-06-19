//
//  KoinApplication.swift
//  KaMPStarteriOS
//
//  Created by Russell Wolf on 6/18/20.
//  Copyright © 2020 Touchlab. All rights reserved.
//

import Foundation
import shared

let koinApplication = KoinKt.doInitKoin { it in
    it.module { it in
        it.single(qualifier: KoinIOSKt.userDefaultsQualifier) { () -> Any in
            // Note that Koin can't handle Objective-C types, so we cast to Any
            UserDefaults(suiteName: "KAMPSTARTER_SETTINGS") as Any
        }
    }
}
let koin = koinApplication.koin
