package vn.edu.ntu.nguyenanhhai.ontap_dangkydichvu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ChaoMungFragment extends Fragment implements View.OnClickListener {
  TextView txtThongBao;
  Button btnThoat;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_chaomung, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    txtThongBao = view.findViewById(R.id.txtThongBao);
    btnThoat = view.findViewById(R.id.btnThoat);

    Bundle bundle = getArguments();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Chào mừng khách hàng: ")
            .append(bundle.getString(DangKyFragment.KEY_NAME))
            .append("\nĐịa chỉ: ")
            .append(bundle.getString(DangKyFragment.KEY_ADDR))
            .append("\nSinh ngày: ")
            .append(bundle.getString(DangKyFragment.KEY_BIRTH))
            .append("\nĐã đăng ký thành công dich vụ: ")
            .append(bundle.getString(DangKyFragment.KEY_DICHVU))
            .append("\nHình thức thanh toán: ")
            .append(bundle.getString(DangKyFragment.KEY_THANHTOAN))
            .append("\nChúng tôi sẽ liên hệ với bạ qua số điện thoại: ")
            .append(bundle.getString(DangKyFragment.KEY_PHONE));
    txtThongBao.setText(stringBuilder.toString());

    btnThoat.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    ((MainActivity)getActivity()).navController.navigateUp();
  }
}
