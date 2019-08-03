package com.ViasApp.JsonClasses

import net.liftweb.json.JsonAST.JField

trait SerializableJson {
  
  def getAtributosJson: List[JField]
}