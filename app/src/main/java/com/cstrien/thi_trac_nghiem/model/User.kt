package com.cstrien.thi_trac_nghiem.model

//implements Parcelable
class User {
    @JvmField
    var id: Int = 0
    @JvmField
    var role: Int = 0
    @JvmField
    var name: String? = null

    //    protected User(Parcel in) {
    @JvmField
    var password: String? = null

    constructor(role: Int, name: String?, password: String?) {
        this.role = role
        this.name = name
        this.password = password
    }

    constructor()

    //        this.id = in.readInt();
    //        this.role = in.readInt();
    //        this.name = in.readString();
    //        this.password = in.readString();
    //    }
    //
    //    public static final Creator<User> CREATOR = new Creator<User>() {
    //        @Override
    //        public User createFromParcel(Parcel in) {
    //            return new User(in);
    //        }
    //
    //        @Override
    //        public User[] newArray(int size) {
    //            return new User[size];
    //        }
    //    };
    //
    //    @Override
    //    public int describeContents() {
    //        return 0;
    //    }
    //
    //    @Override
    //    public void writeToParcel(Parcel dest, int flags) {
    //        dest.writeInt(id);
    //        dest.writeString(name);
    //        dest.writeString(password);
    //        dest.writeInt(role);
    //    }
}
