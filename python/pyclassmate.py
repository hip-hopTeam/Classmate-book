#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_httpauth import HTTPAuth
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
api = Api(app)
db = SQLAlchemy(app)
auth = HTTPAuth(app)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:acm201*@ngrok.codeself.cn:8306/classmatebook'

class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(16))
    phone = db.Column(db.String(12))
    wechat = db.Column(db.String(100))
    mail = db.Column(db.String(100))
    qq = db.Column(db.String(100))
    language = db.Column(db.String(100))

    resource_fields = {
        'username': fields.String,
        'phone': fields.String,
        'wechat': fields.String,
        'mail': fields.String,
        'id': fields.Integer,
        'qq': fields.String,
        'language': fields.String
    }

db.create_all()
parse = reqparse.RequestParser()
parse.add_argument('rate', type=int)

class Register(Resource):
    def post(self):
        username = request.form['username']
        phone = request.form['phone']
        wechat = request.form['wechat']
        mail = request.form['mail']
        qq = request.form['qq']
        language = request.form['language']
        # mail examination
        exist_user = User.query.filter_by(mail=mail).first()
        if exist_user is not None:
            return 'User Exists', 401
        # phone examination
        exist_user = User.query.filter_by(phone=phone).first()
        if exist_user is not None:
            return 'User Exists', 401

        new_user = User(username=username, phone=phone, wechat=wechat, 
                        mail=mail, qq=qq, language=language)
        db.session.add(new_user)
        db.session.commit()
        return 'Successfully', 200

class Delete(Resource):
    def post(self):
        id = request.form['id']
        user = User.query.filter_by(id=id).first()
        if user is None:
            return 'User not found', 401
        db.session.delete(user)
        db.session.commit()

class Alter(Resource):
    def post(self):
        id = request.form['id']
        user = User.query.filter_by(id=id).first()
        if user is None:
            return 'User not found  ', 401
        username = request.form['username']
        if username is not None:
            user.username = username
        phone = request.form['phone']
        if phone is not None:
            user.phone = phone
        wechat = request.form['wechat']
        if wechat is not None:
            user.wechat = wechat
        qq = request.form['qq']
        if qq is not None:
            user.qq = qq
        mail = request.form['mail']
        if mail is not None:
            user.mail = mail
        language = request.form['language']
        if language is not None:
            user.language = language
        db.session.commit()

class Query(Resource):
    @marshal_with(User.resource_fields, envelope='resource')
    def get(self, id):
        user = User.query.get(id)
        return user

api.add_resource(Register, '/register')
api.add_resource(Delete, '/delete')
api.add_resource(Alter, '/alter')
api.add_resource(Query, '/query/<string:id>')

app.run(debug=True)
