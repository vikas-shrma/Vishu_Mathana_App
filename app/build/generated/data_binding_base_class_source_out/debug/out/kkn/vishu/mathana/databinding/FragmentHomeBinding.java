// Generated by view binder compiler. Do not edit!
package kkn.vishu.mathana.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kkn.vishu.mathana.R;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView bottomBanner;

  @NonNull
  public final AppCompatImageView ivAbout;

  @NonNull
  public final AppCompatImageView ivArtist;

  @NonNull
  public final AppCompatImageView ivFlyer;

  @NonNull
  public final AppCompatImageView ivLogo;

  @NonNull
  public final AppCompatImageView ivPainting;

  @NonNull
  public final AppCompatImageView ivPoster;

  @NonNull
  public final ViewPager2 pager;

  @NonNull
  public final ScrollingPagerIndicator pagerIndicator;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView bottomBanner, @NonNull AppCompatImageView ivAbout,
      @NonNull AppCompatImageView ivArtist, @NonNull AppCompatImageView ivFlyer,
      @NonNull AppCompatImageView ivLogo, @NonNull AppCompatImageView ivPainting,
      @NonNull AppCompatImageView ivPoster, @NonNull ViewPager2 pager,
      @NonNull ScrollingPagerIndicator pagerIndicator) {
    this.rootView = rootView;
    this.bottomBanner = bottomBanner;
    this.ivAbout = ivAbout;
    this.ivArtist = ivArtist;
    this.ivFlyer = ivFlyer;
    this.ivLogo = ivLogo;
    this.ivPainting = ivPainting;
    this.ivPoster = ivPoster;
    this.pager = pager;
    this.pagerIndicator = pagerIndicator;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomBanner;
      AppCompatImageView bottomBanner = rootView.findViewById(id);
      if (bottomBanner == null) {
        break missingId;
      }

      id = R.id.ivAbout;
      AppCompatImageView ivAbout = rootView.findViewById(id);
      if (ivAbout == null) {
        break missingId;
      }

      id = R.id.ivArtist;
      AppCompatImageView ivArtist = rootView.findViewById(id);
      if (ivArtist == null) {
        break missingId;
      }

      id = R.id.ivFlyer;
      AppCompatImageView ivFlyer = rootView.findViewById(id);
      if (ivFlyer == null) {
        break missingId;
      }

      id = R.id.ivLogo;
      AppCompatImageView ivLogo = rootView.findViewById(id);
      if (ivLogo == null) {
        break missingId;
      }

      id = R.id.ivPainting;
      AppCompatImageView ivPainting = rootView.findViewById(id);
      if (ivPainting == null) {
        break missingId;
      }

      id = R.id.ivPoster;
      AppCompatImageView ivPoster = rootView.findViewById(id);
      if (ivPoster == null) {
        break missingId;
      }

      id = R.id.pager;
      ViewPager2 pager = rootView.findViewById(id);
      if (pager == null) {
        break missingId;
      }

      id = R.id.pagerIndicator;
      ScrollingPagerIndicator pagerIndicator = rootView.findViewById(id);
      if (pagerIndicator == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, bottomBanner, ivAbout, ivArtist,
          ivFlyer, ivLogo, ivPainting, ivPoster, pager, pagerIndicator);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
