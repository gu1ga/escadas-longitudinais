package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.myNavHostFragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        when(item.itemId){
            R.id.materiaisFragment ->{
                navController.navigate(R.id.materiaisFragment)
            }
            R.id.sobreFragment ->{}
        }
        return super.onOptionsItemSelected(item)
    }
}