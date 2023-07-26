package com.example.qrscanner.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.qrscanner.QrCodeAnalyzer
import com.example.qrscanner.presentation.home.HomeViewModel
import com.example.qrscanner.ui.theme.QRScannerTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerTheme {
                val viewModel by viewModels<HomeViewModel>()
                val balance by viewModel.getBalance.observeAsState()
                var code by remember {
                    mutableStateOf("")
                }
                val context = LocalContext.current
                val lifecycleOwner = LocalLifecycleOwner.current
                val cameraProviderFuture = remember {
                    ProcessCameraProvider.getInstance(context)
                }
                var hasCameraPermission by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { granted ->
                        hasCameraPermission = granted
                    }
                )
                LaunchedEffect(key1 = true) {
                    launcher.launch(android.Manifest.permission.CAMERA)
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (hasCameraPermission) {
                        AndroidView(
                            factory = { context ->
                                val previewView = PreviewView(context)
                                val preview = Preview.Builder().build()
                                val selector = CameraSelector.Builder()
                                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                    .build()
                                preview.setSurfaceProvider(previewView.surfaceProvider)
                                val imageAnalysis = ImageAnalysis.Builder()
                                    .setTargetResolution(
                                        Size(
                                            previewView.width,
                                            previewView.height
                                        )
                                    )
                                    .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                                    .build()
                                imageAnalysis.setAnalyzer(
                                    ContextCompat.getMainExecutor(context),
                                    QrCodeAnalyzer { result ->
                                        code = result
                                        Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
                                    }
                                )
                                try {
                                    cameraProviderFuture.get().bindToLifecycle(
                                        lifecycleOwner,
                                        selector,
                                        preview,
                                        imageAnalysis
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                previewView
                            },
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = balance.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp)
                        )


                    }
                }

                // A surface container using the 'background' color from the theme
                //                Surface(
                //                    modifier = Modifier.fillMaxSize(),
                //                    color = MaterialTheme.colorScheme.background
                //                ) {
                //                    Greeting(name = "ehe")
                //                }
            }


        }
    }

}

@Composable
fun SayHello(text: String) {

    Text(text = text)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

