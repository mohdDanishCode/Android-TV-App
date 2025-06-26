package com.stage.app.presentation.common

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.stage.app.R


@Composable
fun ImageGeneric(
    modifier: Modifier = Modifier,
    src: String,
    imageScale: ContentScale = ContentScale.FillBounds,
    placeholder: Int = R.drawable.skeleton,
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.20)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .respectCacheHeaders(false)
        .crossfade(true)
        .placeholder(placeholder)
        .error(placeholder)
        .build()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            imageLoader = imageLoader,
            model = ImageRequest.Builder(LocalContext.current)
                .data(src)
                .build(),
            contentDescription = null,
            contentScale = imageScale
        )
    }
}


