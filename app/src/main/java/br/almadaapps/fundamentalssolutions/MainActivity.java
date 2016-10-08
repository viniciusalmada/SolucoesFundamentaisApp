package br.almadaapps.fundamentalssolutions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.pow;

public class MainActivity extends CommonActivity implements ListView.OnItemClickListener, View.OnClickListener {
    public static final String F = "%.3f";
    private static final String CARGA_DIST = "Carga distribuida:";
    private static final String UN_KNpM = "KN/m";
    private static final String CARGA_PONT = "Carga concentrada:";
    private static final String UN_KN = "KN";
    private static final String MOMENTO = "Momento:";
    private static final String UN_KNM = "KNm";
    private static final String CARGA_TRI = "Carga triangular:";
    private ImageView ivSolucaoSelecionada;
    private TextView tvCargaMomento;
    private TextView tvComprimento;
    private TextView tvA;
    private TextView tvB;
    private TextView tvResultado;
    private EditText etCargaMomento;
    private EditText etComprimento;
    private EditText etA;
    private EditText etB;
    private Button btCalcular;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list_solucoes);
        ListSolucoesAdapter adapter = new ListSolucoesAdapter(this, getIdsImages());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        initViews();
    }

    protected void initViews() {
        ivSolucaoSelecionada = (ImageView) findViewById(R.id.iv_solucao_selecionada);
        tvCargaMomento = (TextView) findViewById(R.id.tv_carga_momento);
        tvComprimento = (TextView) findViewById(R.id.tv_comprimento);
        tvA = (TextView) findViewById(R.id.tv_a);
        tvB = (TextView) findViewById(R.id.tv_b);
        tvResultado = (TextView) findViewById(R.id.tv_resultado);
        etCargaMomento = (EditText) findViewById(R.id.et_carga_momento);
        etComprimento = (EditText) findViewById(R.id.et_comprimento);
        etA = (EditText) findViewById(R.id.et_a);
        etB = (EditText) findViewById(R.id.et_b);
        btCalcular = (Button) findViewById(R.id.bt_calcular);

        tvCargaMomento.setVisibility(View.GONE);
        etCargaMomento.setVisibility(View.GONE);
        tvComprimento.setVisibility(View.GONE);
        etComprimento.setVisibility(View.GONE);
        tvA.setVisibility(View.GONE);
        etA.setVisibility(View.GONE);
        tvB.setVisibility(View.GONE);
        etB.setVisibility(View.GONE);
        btCalcular.setVisibility(View.GONE);
        btCalcular.setOnClickListener(this);

        etA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double a = Double.parseDouble(String.valueOf(s));
                    double l = Double.parseDouble(etComprimento.getText().toString());
                    double b = l - a;
                    etB.setText(String.valueOf(b));
                } catch (NumberFormatException | NullPointerException ignored) {
                    ignored.printStackTrace();
                }
                if (s.equals(""))
                    etB.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void setABVisibility(boolean toVisible) {
        if (toVisible) {
            tvA.setVisibility(View.VISIBLE);
            tvB.setVisibility(View.VISIBLE);
            etA.setVisibility(View.VISIBLE);
            etB.setVisibility(View.VISIBLE);
        } else {
            tvA.setVisibility(View.GONE);
            tvB.setVisibility(View.GONE);
            etA.setVisibility(View.GONE);
            etB.setVisibility(View.GONE);
        }
    }

    protected void setQLVisibility(boolean toVisible) {
        if (toVisible) {
            tvCargaMomento.setVisibility(View.VISIBLE);
            tvComprimento.setVisibility(View.VISIBLE);
            etCargaMomento.setVisibility(View.VISIBLE);
            etComprimento.setVisibility(View.VISIBLE);
        } else {
            tvCargaMomento.setVisibility(View.GONE);
            tvComprimento.setVisibility(View.GONE);
            etCargaMomento.setVisibility(View.GONE);
            etComprimento.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        ivSolucaoSelecionada.setImageResource(getIdsImages()[position]);
        setQLVisibility(true);
        btCalcular.setVisibility(View.VISIBLE);
        switch (position) {
            case 0:
            case 1:
            case 2:
                tvCargaMomento.setText(CARGA_DIST);
                etCargaMomento.setHint(UN_KNpM);
                setABVisibility(false);
                break;
            case 3:
            case 4:
            case 5:
                tvCargaMomento.setText(CARGA_PONT);
                etCargaMomento.setHint(UN_KN);
                setABVisibility(false);
                break;
            case 6:
                tvCargaMomento.setText(CARGA_PONT);
                etCargaMomento.setHint(UN_KN);
                setABVisibility(true);
                break;
            case 7:
                tvCargaMomento.setText(MOMENTO);
                etCargaMomento.setHint(UN_KNM);
                setABVisibility(true);
                break;
            case 8:
                tvCargaMomento.setText(CARGA_TRI);
                etCargaMomento.setHint(UN_KNpM);
                setABVisibility(false);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                setQLVisibility(false);
                setABVisibility(false);
                tvComprimento.setVisibility(View.VISIBLE);
                etComprimento.setVisibility(View.VISIBLE);
                break;
            default:
                setQLVisibility(false);
                setABVisibility(false);
                btCalcular.setVisibility(View.GONE);

        }
        tvResultado.setText("");
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onClick(View v) {
        if (v instanceof Button) {
            double ma = 0, mb = 0, qa = 0, qb = 0, l, a = 0, b = 0, q = 0, kqa = 0, kqb = 0, kma = 0, kmb = 0, kna = 0, knb = 0;
            try {
                l = Double.parseDouble(etComprimento.getText().toString());
                if (position < 9) {
                    q = Double.parseDouble(etCargaMomento.getText().toString());
                    if (position == 7 || position == 6) {
                        a = Double.parseDouble(etA.getText().toString());
                        b = Double.parseDouble(etB.getText().toString());
                    }
                }
                switch (position) {
                    case 0:
                        ma = (q * pow(l, 2)) / 12;
                        mb = -ma;
                        qa = qb = (q * l) / 2;
                        break;
                    case 1:
                        ma = 0;
                        mb = -(q * pow(l, 2)) / 8;
                        qa = (3 * q * l) / 8;
                        qb = (5 * q * l) / 8;
                        break;
                    case 2:
                        mb = 0;
                        ma = (q * pow(l, 2)) / 8;
                        qa = (5 * q * l) / 8;
                        qb = (3 * q * l) / 8;
                        break;
                    case 3:
                        ma = (q * l) / 8;
                        mb = -(q * l) / 8;
                        qa = qb = q / 2;
                        break;
                    case 4:
                        ma = 0;
                        mb = -(3 * q * l) / 16;
                        qa = (5 * q) / 16;
                        qb = (11 * q) / 16;
                        break;
                    case 5:
                        mb = 0;
                        ma = (3 * q * l) / 16;
                        qa = (11 * q) / 16;
                        qb = (5 * q) / 16;
                        break;
                    case 6:
                        ma = (q * a * pow(b, 2)) / pow(l, 2);
                        mb = -(q * pow(a, 2) * b) / pow(l, 2);
                        qa = (q * pow(b, 2) * (3 * a + b)) / (pow(l, 3));
                        qb = (q * pow(a, 2) * (a + 3 * b)) / (pow(l, 3));
                        break;
                    case 7:
                        ma = (q * b * (2 * a - b)) / (pow(l, 2));
                        mb = (q * a * (2 * b - a)) / (pow(l, 2));
                        qa = (6 * q * a * b) / (pow(l, 3));
                        qb = -qa;
                        break;
                    case 8:
                        ma = (q * pow(l, 2)) / 30;
                        mb = (q * pow(l, 2)) / 20;
                        qa = (3 * q * l) / 20;
                        qb = (7 * q * l) / 20;
                        break;
                    case 9:
                        kna = 1 / l;
                        knb = -kna;
                        break;
                    case 10:
                        knb = 1 / l;
                        kna = -knb;
                        break;
                    case 11:
                        kqa = 12 / (pow(l, 3));
                        kqb = -12 / (pow(l, 3));
                        kma = 6 / (pow(l, 2));
                        kmb = kma;
                        break;
                    case 12:
                        kqa = -12 / (pow(l, 3));
                        kqb = 12 / (pow(l, 3));
                        kma = -6 / (pow(l, 2));
                        kmb = kma;
                        break;
                    case 13:
                        kqa = 6 / (pow(l, 2));
                        kqb = -kqa;
                        kma = 4 / l;
                        kmb = 2 / l;
                        break;
                    case 14:
                        kqa = 6 / (pow(l, 2));
                        kqb = -kqa;
                        kma = 2 / l;
                        kmb = 4 / l;
                        break;
                }
                String resultado;
                if (position < 9) {
                    resultado =
                            "Ma = " + String.format(F, ma) + " KNm\n" +
                                    "Qa = " + String.format(F, qa) + " KN\n" +
                                    "Mb = " + String.format(F, mb) + " KNm\n" +
                                    "Qb = " + String.format(F, qb) + " KN\n";
                } else {
                    resultado =
                            "KQa = " + String.format(F, kqa) + "EI KN/?\n" +
                                    "KQb = " + String.format(F, kqb) + "EI KN/?\n" +
                                    "KMa = " + String.format(F, kma) + "EI KNm/?\n" +
                                    "KMb = " + String.format(F, kmb) + "EI KNm/?\n" +
                                    "KNa = " + String.format(F, kna) + "EA KN/?\n" +
                                    "KNb = " + String.format(F, knb) + "EA KN/?\n";
                }
                //                tvResultado.setText(resultado);
                showOnDialog(resultado);
                ma = mb = qa = qb = kna = knb = kma = kmb = kqa = kqb = 0;
            } catch (NullPointerException | IllegalArgumentException e) {
                e.printStackTrace();
                Toast.makeText(this, "Insira um número válido!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ListSolucoesAdapter extends ArrayAdapter<Integer> {
        private Context context;
        private Integer[] objects;

        private ListSolucoesAdapter(Context context, Integer[] objects) {
            super(context, 0, objects);
            this.context = context;
            this.objects = objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Integer id = objects[position];

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_list_solucoes, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView.setImageResource(id);

            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
        }
    }
}