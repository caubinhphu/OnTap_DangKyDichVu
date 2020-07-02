package vn.edu.ntu.nguyenanhhai.ontap_dangkydichvu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class DangKyFragment extends Fragment implements View.OnClickListener {
  public static final String KEY_NAME = "name";
  public static final String KEY_PHONE = "phone";
  public static final String KEY_ADDR = "address";
  public static final String KEY_BIRTH = "day-of-birth";
  public static final String KEY_THANHTOAN = "thanh-toan";
  public static final String KEY_DICHVU = "dich-vu";

  EditText edtName, edtBirth, edtPhone, edtAddr;
  ImageView imvBirth;
  Button btnDangKy;
//  RadioButton rdbTienMat, rdbNganHang, rdbVi;
  RadioGroup rdgThanhToan;
  Spinner spDichVu;
  String[] dsDichVu;

  NavController navController;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dangky, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    edtName = view.findViewById(R.id.edtName);
    edtAddr = view.findViewById(R.id.edtAddr);
    edtBirth = view.findViewById(R.id.edtBirth);
    edtPhone = view.findViewById(R.id.edtPhone);
    btnDangKy = view.findViewById(R.id.btnDangKy);
    imvBirth = view.findViewById(R.id.imvBirth);
//    rdbTienMat = view.findViewById(R.id.rdbTienMat);
//    rdbNganHang = view.findViewById(R.id.rdbNganHang);
//    rdbVi = view.findViewById(R.id.rdbVi);
    spDichVu = view.findViewById(R.id.spDichVu);
    rdgThanhToan = view.findViewById(R.id.rdgThanhToan);

    dsDichVu = ((MainActivity)getActivity()).getResources().getStringArray(R.array.dichvu);
    spDichVu.setAdapter(new ArrayAdapter<>(((MainActivity)getActivity()), android.R.layout.simple_list_item_1, dsDichVu));

    btnDangKy.setOnClickListener(this);
    imvBirth.setOnClickListener(this);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    navController = NavHostFragment.findNavController(this);
    ((MainActivity)getActivity()).navController = navController;
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.imvBirth: chonNgay(); break;
      case R.id.btnDangKy: dangKy(); break;
    }
  }

  private void chonNgay() {
    final Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayOfMonth)
                .append("/")
                .append(month + 1)
                .append("/")
                .append(year);
        edtBirth.setText(stringBuilder.toString());
      }
    };

    DatePickerDialog datePickerDialog = new DatePickerDialog(
            getActivity(),
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
    );

    datePickerDialog.show();
  }

  private void dangKy() {
    String name = edtName.getText().toString();
    String phone = edtPhone.getText().toString();
    String birth = edtBirth.getText().toString();
    String addr = edtAddr.getText().toString();
    String thanhToan = "";
    switch (rdgThanhToan.getCheckedRadioButtonId()) {
      case R.id.rdbTienMat: thanhToan =  "Tiền mặt"; break;
      case R.id.rdbNganHang: thanhToan =  "Ngân hàng"; break;
      case R.id.rdbVi: thanhToan =  "Ví điện tử"; break;
    }
    String dichVu = spDichVu.getSelectedItem().toString();
    if (name.length() > 0 && phone.length() > 0 && birth.length() > 0 && addr.length() > 0) {
      Bundle bundle = new Bundle();
      bundle.putString(KEY_NAME, name);
      bundle.putString(KEY_PHONE, phone);
      bundle.putString(KEY_ADDR, addr);
      bundle.putString(KEY_BIRTH, birth);
      bundle.putString(KEY_THANHTOAN, thanhToan);
      bundle.putString(KEY_DICHVU, dichVu);

      navController.navigate(R.id.action_dangKyFragment_to_chaoMungFragment, bundle);
    } else {
      Toast.makeText(getActivity(), "Chưa nhập đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
    }

  }
}
