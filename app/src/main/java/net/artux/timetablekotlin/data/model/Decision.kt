package net.artux.timetablekotlin.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Decision{

    @SerializedName("ResultID")
    @Expose
    private var resultID: String? = null

    @SerializedName("Text")
    @Expose
    private var text: String? = null

    @SerializedName("VersionDate")
    @Expose
    private var versionDate: String? = null

    @SerializedName("StoredFileID")
    @Expose
    private var storedFileID: String? = null

    @SerializedName("StoredFile")
    @Expose
    private var storedFile: String? = null

    @SerializedName("StoredFileSize")
    @Expose
    private var storedFileSize: String? = null

    @SerializedName("ResultText")
    @Expose
    private var resultText: String? = null

    @SerializedName("Result")
    @Expose
    private var result: String? = null

    fun getResultID(): String? {
        return resultID
    }

    fun setResultID(resultID: String?) {
        this.resultID = resultID
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String?) {
        this.text = text
    }

    fun getVersionDate(): String? {
        return versionDate
    }

    fun setVersionDate(versionDate: String?) {
        this.versionDate = versionDate
    }

    fun getStoredFileID(): String? {
        return storedFileID
    }

    fun setStoredFileID(storedFileID: String?) {
        this.storedFileID = storedFileID
    }

    fun getStoredFile(): String? {
        return storedFile
    }

    fun setStoredFile(storedFile: String?) {
        this.storedFile = storedFile
    }

    fun getStoredFileSize(): String? {
        return storedFileSize
    }

    fun setStoredFileSize(storedFileSize: String?) {
        this.storedFileSize = storedFileSize
    }

    fun getResultText(): String? {
        return resultText
    }

    fun setResultText(resultText: String?) {
        this.resultText = resultText
    }

    fun getResult(): String? {
        return result
    }

    fun setResult(result: String?) {
        this.result = result
    }

}