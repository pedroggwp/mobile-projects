package com.jamiltondamasceno.aulacomponentesinterfaceviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.jamiltondamasceno.aulacomponentesinterfaceviewbinding.databinding.ActivityFormularioBinding

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        spinnerExibicao()
        with( binding ){

            btnEnviar.setOnClickListener { view ->
                //checkbox()
                //radioButton()
                //switchToggle()
                //exibirSnackBar(view)
                //Toast.makeText(, "", Toast.LENGTH_SHORT).show()
                //caixaDialogAlerta()
                spinnerSelecionarItem()

            }

            /*toggleAtivo.setOnClickListener {  }
            toggleAtivo.setOnCheckedChangeListener { buttonView, isChecked ->

            }*/
            /*rbMasculino.setOnClickListener {  }
            rbMasculino.setOnCheckedChangeListener { buttonView, isChecked -> }*/
            /*cbConfirmacao.setOnCheckedChangeListener { _, isChecked ->
                val resultado = if( isChecked ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }*/
            /*cbConfirmacao.setOnClickListener {
                val selecionado = cbConfirmacao.isChecked
                val resultado = if( selecionado ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }*/

        }

    }

    private fun spinnerSelecionarItem() {

        val itemSelecionado = binding.spinnerCategorias.selectedItem
        val itemPosicao = binding.spinnerCategorias.selectedItemPosition

        if( itemPosicao == 0 ){
            binding.textResultado.text = "Selecione um item"
        }else{
            binding.textResultado.text = "selecionado: $itemSelecionado / pos: $itemPosicao"
        }

    }

    private fun spinnerExibicao() {

        /*val categorias = listOf(
            "Selecione uma categoria",
            "Eletrônicos", "Roupas", "Móveis", "Roupas"
        )*/
        /*val categorias = resources.getStringArray(
            R.array.categorias
        )
        binding.spinnerCategorias.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categorias
        )*/

        binding.spinnerCategorias.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_dropdown_item
        )

        /*binding.spinnerCategorias.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //val itemSelecionado = parent?.getItemAtPosition(position)
                val itemSelecionado = parent?.selectedItem
                binding.textResultado.text = itemSelecionado.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }*/

    }








    private fun caixaDialogAlerta() {

        AlertDialog.Builder(this)
            .setTitle("Confirmar exclusão do item?")
            .setMessage("Tem certeza que quer remover?")
            .setNegativeButton("cancelar"){ dialog, posicao ->
                Toast.makeText(this, "Cancelar ($posicao)", Toast.LENGTH_SHORT).show()
                //dialog.dismiss()
            }
            .setPositiveButton("remover"){ dialog, posicao ->
                Toast.makeText(this, "Remover ($posicao)", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .create()
            .show()

        /*val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão do item?")
        alertBuilder.setMessage("Tem certeza que quer remover?")

        alertBuilder.setNegativeButton("cancelar"){ dialog, posicao ->
            Toast.makeText(this, "Cancelar ($posicao)", Toast.LENGTH_SHORT).show()
            //dialog.dismiss()
        }

        alertBuilder.setPositiveButton("remover"){ dialog, posicao ->
            Toast.makeText(this, "Remover ($posicao)", Toast.LENGTH_SHORT).show()
        }

        alertBuilder.setCancelable(false)
        alertBuilder.setNeutralButton("ajuda"){ dialog, posicao ->
            Toast.makeText(this, "Ajuda ($posicao)", Toast.LENGTH_SHORT).show()
        }
        alertBuilder.setIcon(R.drawable.ic_alerta_24)

        val alertDialog = alertBuilder.create()
        alertDialog.show()*/

    }









    private fun exibirSnackBar(view: View) {

        val snackBar = Snackbar.make(
            view,
            "Alteração feita com sucesso!",
            Snackbar.LENGTH_LONG
        )

        snackBar.setAction("Desfazer"){
            Toast.makeText(this, "Desfeito", Toast.LENGTH_SHORT).show()
        }

        /*snackBar.setTextColor(
            //resources.getColor(R.color.purple_200)
            ContextCompat.getColor(
                this,
                R.color.purple_200
            )
        )

        snackBar.setActionTextColor(
            ContextCompat.getColor(
                this,
                R.color.teal_200
            )
        )

        snackBar.setBackgroundTint(
            ContextCompat.getColor(
                this,
                android.R.color.holo_orange_dark
            )
        )*/

        snackBar.show()

    }





    private fun switchToggle() {

        val switchMarcado = binding.switchNotificacoes.isChecked
        val toggleMarcado = binding.toggleAtivo.isChecked

        val texto = "Switch: $switchMarcado toggle: $toggleMarcado"
        binding.textResultado.text = texto

    }

    private fun radioButton() {

        val masculino = binding.rbMasculino.isChecked
        //binding.textResultado.text = if(masculino) "Masculino" else "Feminino"

        val idItemSelecionado = binding.rgSexo.checkedRadioButtonId
        binding.textResultado.text = when( idItemSelecionado ){
            R.id.rbMasculino -> "Masculino"
            R.id.rbFeminino -> "Femino"
            else -> "Nada selecionado"
        }

        binding.rgSexo.clearCheck()




        /*if( selecionadoMasculino ){

        }else if( binding.rbFeminino.isChecked ){

        }else{

        }*/

    }

    private fun checkbox() {
        val selecionado = binding.cbConfirmacao.isChecked
        val resultado = if( selecionado ) "Sim" else "Não"
        binding.textResultado.text = "valor selecionado: $resultado"
    }
}