package com.example.noteapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_note_editor.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import android.R.attr.data
import android.app.Activity
import android.graphics.BitmapFactory
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note_editor.view.*
import java.net.URI


class NewNoteActivity :AppCompatActivity() {
    private var noteId :Long = LONG_DEFAULT_VALUE.toLong()
    private lateinit var menuEditor: Menu
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var noteTitle: String
    private lateinit var noteContent: String
    private lateinit var noteColor: String
    private lateinit var noteCurrentColor: String
    private lateinit var noteDate: String
    //TODO
    private  var notePicture = "empty"

    private val dataFormatter = SimpleDateFormat("MMM dd yyyy HH:mma")

    val ATTACH_REQUEST_CODE = 100
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteColor = ContextCompat.getColor(this, R.color.note_color_yellow).toString()
        noteCurrentColor = ContextCompat.getColor(this, R.color.note_color_yellow).toString()
        //imageView.setImageBitmap()
        var date = Date();
        noteDate = dataFormatter.format(date)
        tv_day_editor.setText(noteDate)

        val data = intent.extras
        noteId = data.getLong(EXTRA_ID)

        picture.setOnClickListener{
            dispatchTakePictureIntent()
        }
        attach.setOnClickListener{
             val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, ATTACH_REQUEST_CODE );
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_new_note, menu)
        menuEditor = menu

        if (noteId != LONG_DEFAULT_VALUE.toLong()) {
            val noteGet: Note? = noteViewModel.getNoteWithId(noteId)
            noteTitle = noteGet?.title!!
            noteContent = noteGet?.content!!
            noteDate = noteGet?.dateModified!!
            noteColor = noteGet?.color!!
            noteCurrentColor = noteColor
            notePicture = noteGet?.picture
            when (noteColor) {
                ContextCompat.getColor(this, R.color.stroke_color_yellow).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_yellow)
                    ll_editor.background = getDrawable(R.drawable.note_item_yellow)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_yellow)
                }

                ContextCompat.getColor(this, R.color.stroke_color_green).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_green)
                    ll_editor.background = getDrawable(R.drawable.note_item_green)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_green)
                }

                ContextCompat.getColor(this, R.color.stroke_color_pink).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_pink)
                    ll_editor.background = getDrawable(R.drawable.note_item_pink)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_pink)
                }

                ContextCompat.getColor(this, R.color.stroke_color_purple).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_purple)
                    ll_editor.background = getDrawable(R.drawable.note_item_purple)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_purple)
                }

                ContextCompat.getColor(this, R.color.stroke_color_blue).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_blue)
                    ll_editor.background = getDrawable(R.drawable.note_item_blue)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_blue)
                }

                ContextCompat.getColor(this, R.color.stroke_color_gray).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_gray)
                    ll_editor.background = getDrawable(R.drawable.note_item_gray)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_gray)
                }
            }

            ed_title.setText(noteTitle)
            ed_content.setText(noteTitle)
            tv_day_editor.setText(noteDate)
            if(!notePicture.equals("empty")) {
                /*val temp = notePicture.split(",").toTypedArray()
                val textView = findViewById<TextView>(R.id.ed_content)
                textView.setText(temp[0]+"\n"+temp[1]+"\n"+temp[2]+"\n"+temp.size)*/

                val linear = findViewById<LinearLayout>(R.id.pictures)
                var newImage : ImageView
                val temp = notePicture.split(",").toTypedArray()
                for( i in 0 until (temp.size)){
                    newImage = ImageView(this)
                    newImage.setLayoutParams(android.view.ViewGroup.LayoutParams(160,120))
                    linear.addView(newImage)
                    newImage.setImageURI(Uri.fromFile(File(temp[i])))
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_color_yellow -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_yellow).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_yellow)
                ll_editor.background = getDrawable(R.drawable.note_item_yellow)
                bt_line_editor.background = getDrawable(R.drawable.top_line_yellow)
                return true
            }

            R.id.menu_color_green -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_green).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_green)
                ll_editor.background = getDrawable(R.drawable.note_item_green)
                bt_line_editor.background = getDrawable(R.drawable.top_line_green)
                return true
            }

            R.id.menu_color_pink -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_pink).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_pink)
                ll_editor.background = getDrawable(R.drawable.note_item_pink)
                bt_line_editor.background = getDrawable(R.drawable.top_line_pink)
                return true
            }

            R.id.menu_color_purple -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_purple).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_purple)
                ll_editor.background = getDrawable(R.drawable.note_item_purple)
                bt_line_editor.background = getDrawable(R.drawable.top_line_purple)
                return true
            }

            R.id.menu_color_blue -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_blue).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_blue)
                ll_editor.background = getDrawable(R.drawable.note_item_blue)
                bt_line_editor.background = getDrawable(R.drawable.top_line_blue)
                return true
            }

            R.id.menu_color_gray -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_gray).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_gray)
                ll_editor.background = getDrawable(R.drawable.note_item_gray)
                bt_line_editor.background = getDrawable(R.drawable.top_line_gray)
                return true
            }

            R.id.menu_editor_delete -> {
                if (noteId != LONG_DEFAULT_VALUE.toLong()) {
                    noteViewModel.deleteNote(noteViewModel.getNoteWithId(noteId)!!)
                }
                finish()
            }

            R.id.menu_done -> {
                val titleGet = ed_title.text.toString()
                val contentGet = ed_content.text.toString()

                if (noteId != LONG_DEFAULT_VALUE.toLong()) {
                    if (titleGet != noteTitle || contentGet != noteContent || noteColor != noteCurrentColor) {
                        var date = Date()
                        //noteColor = noteCurrentColor
                        noteDate = dataFormatter.format(date)

                    }

                    val note = Note(noteId, titleGet, contentGet, noteDate, noteCurrentColor, notePicture)
                    noteViewModel.insertNote(note)
                } else {
                    var date = Date()
                    noteDate = dataFormatter.format(date)
                    val note = Note(null, titleGet, contentGet, noteDate, noteCurrentColor, notePicture)
                    noteViewModel.insertNote(note)
                }
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //---------------------------------------Function for picture----------------------------//
    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val textView = findViewById<TextView>(R.id.ed_content)
        var layout = findViewById<LinearLayout>(R.id.pictures);
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                createImageFile()
                val imageBitmap = data?.extras?.get("data") as Bitmap
                //
                val file = createImageFile()
                if(notePicture.equals("empty")){
                    notePicture = file.absolutePath
                }
                else
                    notePicture = notePicture+ "," +file.absolutePath
                file.writeBitmap(imageBitmap, Bitmap.CompressFormat.PNG, 80)
                val newImageView = ImageView(this)
                newImageView.setLayoutParams(android.view.ViewGroup.LayoutParams(160,120))
                 layout.addView(newImageView)
                //val imageView: ImageView = findViewById(R.id.image)
                val imageURI = Uri.fromFile(file)
                newImageView.setImageURI(imageURI)
                textView.setText(notePicture)
            }
            ATTACH_REQUEST_CODE -> {
                var imageUri = data?.getData()

                val imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri)
                val file = createImageFile()
                if(notePicture.equals("empty")){
                    notePicture = file.absolutePath
                }
                else
                    notePicture = notePicture+ "," +file.absolutePath
                file.writeBitmap(imageBitmap, Bitmap.CompressFormat.PNG, 80)
                val newImageView = ImageView(this)
                newImageView.setLayoutParams(android.view.ViewGroup.LayoutParams(160,120))
                layout.addView(newImageView)
                //val imageView: ImageView = findViewById(R.id.image)
                val imageURI = Uri.fromFile(file)
                newImageView.setImageURI(imageURI)
                textView.setText(notePicture)
                //TODO make a copy picture
                /*notePicture = getPathFromURI(imageUri)!!

                textView.setText(getPathFromURI(imageUri))
                val path = getPathFromURI(imageUri)
                val f = File(path)
                textView.setText(imageUri.toString()+"\n"+f.toString())
                imageUri = Uri.fromFile(f)
                imageView.setImageURI(imageUri)*/
            }
        }
    }
    /*fun getPathFromURI( contentURI: Uri?) : String?{
        var res: String? = null
        var proj: Array<String> = emptyArray()
        val cursor = getContentResolver().query(contentURI, proj, null, null, null)
        if (cursor.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }*/
    private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
        outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
        }
    }
    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}