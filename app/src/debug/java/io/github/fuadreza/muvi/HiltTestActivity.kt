package io.github.fuadreza.muvi

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * I add this to be able to tests Fragment using FragmentScenario
 * Because Hilt Fragment need to be attached to Activity
 */
@AndroidEntryPoint
class HiltTestActivity: AppCompatActivity() {
}