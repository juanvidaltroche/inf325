package com.juan.restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
	ArrayList<ArrayList<Object>> listaCompra = new ArrayList<ArrayList<Object>>();
	int iImagen = -1;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p01,
				"CHARQUEKAN", 25)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p02,
				"PAPAS A LA HUANCAYNA", 30)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p03,
				"MAJADITO", 15)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p04,
				"PIQUE MACHO", 50)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p05,
				"HAMBURGUESA SIMPLE", 18)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p06,
				"LOMO MONTADO", 20)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p07,
				"PLATO PACEÑO", 30)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p08,
				"SAJTA DE POLLO", 25)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p09,
				"MILANESA DE CARNE", 26)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p10,
				"RAMEN CON POLLO", 20)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p11,
				"POLLO DORADO", 50)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p12,
				"SALCHIPAPA", 18)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p13,
				"PULPO AL GUSTO", 45)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p14,
				"CHICHARRON POLLO", 30)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p15,
				"SOPITA DE FIDEO", 10)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p16,
				"CHICHARRON CERDO", 40)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p17, "ISPI",
				25)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p18, "CHAIRO",
				20)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p19,
				"FILETE CON PURÉ", 30)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p20,
				"AJI DE FIDEO", 18)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p21,
				"SUSHI SUELTITOS", 12)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p22,
				"SUSHI COMPLETO", 70)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p23,
				"AJI DE RACACHA", 15)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p24,
				"FILETE MIGÑON", 45)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p25,
				"PORCION DE TRUCHA DORADA", 25)));
		lista.add(new ArrayList<Object>(Arrays.asList(R.drawable.p26,
				"SILPANCHO", 25)));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void siguiente(View v) {

		iImagen++;

		int tamano = lista.size();

		if (iImagen < tamano) {
			mostrar(iImagen);
		} else {
			iImagen = 0;
			mostrar(iImagen);
		}
	}

	public void atras(View v) {

		iImagen--;

		if (iImagen >= 0) {
			mostrar(iImagen);
		} else {
			iImagen = 25;
			mostrar(iImagen);
		}
	}

	public void mostrar(int i) {
		ImageView fotoComida = (ImageView) findViewById(R.id.imageView1);
		TextView nombre_plato = (TextView) findViewById(R.id.textView2);
		TextView precio_plato = (TextView) findViewById(R.id.textView4);
		TextView msg = (TextView) findViewById(R.id.textView5);

		tupla = lista.get(i);

		msg.setText(String.valueOf(i));
		((ImageView) fotoComida).setImageResource((Integer) tupla.get(0));
		nombre_plato.setText(String.valueOf(tupla.get(1)));
		precio_plato.setText(String.valueOf(tupla.get(2)));
	}

	ArrayList<Object> tupla;

	public void comprar(View v) {
		try {
			if (iImagen >= 0) {
				tupla = lista.get(iImagen);
				listaCompra.add(new ArrayList<Object>(Arrays.asList(
						tupla.get(1), tupla.get(2))));
				Toast.makeText(this, "1 " + tupla.get(1) + " agregado",
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	public void devolver(View v) {
		if (iImagen >= 0) {
			tupla = lista.get(iImagen);

			int indice = buscar((String) tupla.get(1));
			if (indice != -1) {
				Toast.makeText(this, "1 " + tupla.get(1) + " eliminado",
						Toast.LENGTH_SHORT).show();
				listaCompra.remove(indice);

			} else {
				Toast.makeText(this, "No se encuentra!!!", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	public void facturar(View v) {

		HashMap<String, Integer> acumulador = new HashMap<String, Integer>();
		HashMap<String, Integer> contadorNombresPlatos = new HashMap<String, Integer>();
		HashMap<String, Integer> valoresUnitarios = new HashMap<String, Integer>();
		int sumaTotalValores = 0;

		for (ArrayList<Object> tpl : listaCompra) {
			String nombre = (String) tpl.get(0);
			int valor = (Integer) tpl.get(1);
			sumaTotalValores += valor;

			if (acumulador.containsKey(tpl.get(0))) {

				acumulador.put((String) tpl.get(0), acumulador.get(nombre)
						+ valor);
				contadorNombresPlatos.put(nombre,
						contadorNombresPlatos.get(nombre) + 1);

			} else {

				acumulador.put(nombre, valor);
				contadorNombresPlatos.put(nombre, 1);
				valoresUnitarios.put(nombre, valor);

			}
		}

		// Imprimir el resultado
		for (String nombre : acumulador.keySet()) {
			System.out.println("Nombre: " + nombre + ", Suma de valores: "
					+ acumulador.get(nombre));
		}

		StringBuilder mensaje = new StringBuilder("Resultados:\n");
		for (String nombre : acumulador.keySet()) {
			mensaje
					.append(contadorNombresPlatos.get(nombre))
					.append("|")
					.append(nombre)
					.append("|pu:")
					.append(valoresUnitarios.get(nombre))
					.append("Bs|Sub:")
					.append(acumulador.get(nombre) + "")
					.append("\n__________________\n");
					
		}
		mensaje.append("__________________\n");
		mensaje.append("TOTAL: " + sumaTotalValores + " Bs.");
		Toast.makeText(this, mensaje.toString(), Toast.LENGTH_LONG).show();
	}

	public int buscar(String textoBuscado) {

		int indice = -1;

		// Iterar sobre la lista principal
		for (int i = 0; i < listaCompra.size(); i++) {
			Object item = listaCompra.get(i);

			// Verificar si es una lista interna
			if (item instanceof ArrayList) {
				ArrayList<?> tupla = (ArrayList<?>) item;

				// Comprobar si el primer elemento coincide con el buscado
				if (tupla.get(0).equals(textoBuscado)) {
					indice = i;
					break; // Salir del bucle al encontrar el elemento
				}
			}
		}

		return indice;
	}
}
