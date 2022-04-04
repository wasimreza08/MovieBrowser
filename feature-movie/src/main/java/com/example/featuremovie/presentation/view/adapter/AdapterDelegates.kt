package  com.example.featuremovie.presentation.view.adapter

import com.example.corecommon.ext.setImage
import com.example.featuremovie.R
import com.example.featuremovie.databinding.ItemListBinding
import com.example.featuremovie.presentation.uimodel.Movie
import com.example.featuremovie.presentation.util.Constants.CORNER_RADIUS
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun itemUIDelegate() =
    adapterDelegateViewBinding<Movie, Movie, ItemListBinding>(
        { layoutInflater, parent -> ItemListBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            with(binding)
            {
                poster.setImage(progress, item.image, R.drawable.ic_broken_image, CORNER_RADIUS)
                title.text = item.title
                releaseDate.text = item.releaseDate?.let {
                    android.text.format.DateFormat.format("yyyy", it)
                }
                rating.text = "${item.voteAverage ?: 0}"
            }
        }
    }
