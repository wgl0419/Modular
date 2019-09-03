package cn.leo.base.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.leo.base.db.bean.UserDetailInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_DETAIL_INFO".
*/
public class UserDetailInfoDao extends AbstractDao<UserDetailInfo, Long> {

    public static final String TABLENAME = "USER_DETAIL_INFO";

    /**
     * Properties of entity UserDetailInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID");
        public final static Property Nickname = new Property(2, String.class, "nickname", false, "NICKNAME");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property Sex = new Property(4, int.class, "sex", false, "SEX");
        public final static Property Address = new Property(5, String.class, "address", false, "ADDRESS");
        public final static Property Channel = new Property(6, long.class, "channel", false, "CHANNEL");
        public final static Property Email = new Property(7, String.class, "email", false, "EMAIL");
        public final static Property Imei = new Property(8, String.class, "imei", false, "IMEI");
        public final static Property Ip = new Property(9, String.class, "ip", false, "IP");
        public final static Property Remarks = new Property(10, String.class, "remarks", false, "REMARKS");
        public final static Property Status = new Property(11, String.class, "status", false, "STATUS");
        public final static Property Version = new Property(12, long.class, "version", false, "VERSION");
        public final static Property Birthday = new Property(13, long.class, "birthday", false, "BIRTHDAY");
        public final static Property HeadImage = new Property(14, String.class, "headImage", false, "HEAD_IMAGE");
        public final static Property QrCode = new Property(15, String.class, "qrCode", false, "QR_CODE");
        public final static Property InvitationCode = new Property(16, String.class, "invitationCode", false, "INVITATION_CODE");
    }


    public UserDetailInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserDetailInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_DETAIL_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USER_ID\" TEXT UNIQUE ," + // 1: userId
                "\"NICKNAME\" TEXT," + // 2: nickname
                "\"PHONE\" TEXT," + // 3: phone
                "\"SEX\" INTEGER NOT NULL ," + // 4: sex
                "\"ADDRESS\" TEXT," + // 5: address
                "\"CHANNEL\" INTEGER NOT NULL ," + // 6: channel
                "\"EMAIL\" TEXT," + // 7: email
                "\"IMEI\" TEXT," + // 8: imei
                "\"IP\" TEXT," + // 9: ip
                "\"REMARKS\" TEXT," + // 10: remarks
                "\"STATUS\" TEXT," + // 11: status
                "\"VERSION\" INTEGER NOT NULL ," + // 12: version
                "\"BIRTHDAY\" INTEGER NOT NULL ," + // 13: birthday
                "\"HEAD_IMAGE\" TEXT," + // 14: headImage
                "\"QR_CODE\" TEXT," + // 15: qrCode
                "\"INVITATION_CODE\" TEXT);"); // 16: invitationCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_DETAIL_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserDetailInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(3, nickname);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
        stmt.bindLong(5, entity.getSex());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
        stmt.bindLong(7, entity.getChannel());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(8, email);
        }
 
        String imei = entity.getImei();
        if (imei != null) {
            stmt.bindString(9, imei);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(10, ip);
        }
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(11, remarks);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(12, status);
        }
        stmt.bindLong(13, entity.getVersion());
        stmt.bindLong(14, entity.getBirthday());
 
        String headImage = entity.getHeadImage();
        if (headImage != null) {
            stmt.bindString(15, headImage);
        }
 
        String qrCode = entity.getQrCode();
        if (qrCode != null) {
            stmt.bindString(16, qrCode);
        }
 
        String invitationCode = entity.getInvitationCode();
        if (invitationCode != null) {
            stmt.bindString(17, invitationCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserDetailInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(3, nickname);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
        stmt.bindLong(5, entity.getSex());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(6, address);
        }
        stmt.bindLong(7, entity.getChannel());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(8, email);
        }
 
        String imei = entity.getImei();
        if (imei != null) {
            stmt.bindString(9, imei);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(10, ip);
        }
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(11, remarks);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(12, status);
        }
        stmt.bindLong(13, entity.getVersion());
        stmt.bindLong(14, entity.getBirthday());
 
        String headImage = entity.getHeadImage();
        if (headImage != null) {
            stmt.bindString(15, headImage);
        }
 
        String qrCode = entity.getQrCode();
        if (qrCode != null) {
            stmt.bindString(16, qrCode);
        }
 
        String invitationCode = entity.getInvitationCode();
        if (invitationCode != null) {
            stmt.bindString(17, invitationCode);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserDetailInfo readEntity(Cursor cursor, int offset) {
        UserDetailInfo entity = new UserDetailInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nickname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.getInt(offset + 4), // sex
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // address
            cursor.getLong(offset + 6), // channel
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // email
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // imei
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // ip
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // remarks
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // status
            cursor.getLong(offset + 12), // version
            cursor.getLong(offset + 13), // birthday
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // headImage
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // qrCode
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16) // invitationCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserDetailInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNickname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSex(cursor.getInt(offset + 4));
        entity.setAddress(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setChannel(cursor.getLong(offset + 6));
        entity.setEmail(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImei(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIp(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRemarks(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setStatus(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setVersion(cursor.getLong(offset + 12));
        entity.setBirthday(cursor.getLong(offset + 13));
        entity.setHeadImage(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setQrCode(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setInvitationCode(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserDetailInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserDetailInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserDetailInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
