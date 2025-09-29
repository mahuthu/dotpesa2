package com.example.smscallcapture.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.smscallcapture.data.models.SmsEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SmsDao_Impl implements SmsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SmsEntity> __insertionAdapterOfSmsEntity;

  private final EntityDeletionOrUpdateAdapter<SmsEntity> __updateAdapterOfSmsEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSmsStatus;

  public SmsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSmsEntity = new EntityInsertionAdapter<SmsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `sms` (`id`,`sender`,`message`,`receivedDate`,`uploadedDate`,`status`,`branchId`,`deviceId`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final SmsEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSender() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSender());
        }
        if (entity.getMessage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMessage());
        }
        statement.bindLong(4, entity.getReceivedDate());
        if (entity.getUploadedDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getUploadedDate());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
        if (entity.getBranchId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBranchId());
        }
        if (entity.getDeviceId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeviceId());
        }
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getUpdatedAt());
      }
    };
    this.__updateAdapterOfSmsEntity = new EntityDeletionOrUpdateAdapter<SmsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `sms` SET `id` = ?,`sender` = ?,`message` = ?,`receivedDate` = ?,`uploadedDate` = ?,`status` = ?,`branchId` = ?,`deviceId` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final SmsEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSender() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSender());
        }
        if (entity.getMessage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMessage());
        }
        statement.bindLong(4, entity.getReceivedDate());
        if (entity.getUploadedDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getUploadedDate());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
        if (entity.getBranchId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBranchId());
        }
        if (entity.getDeviceId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeviceId());
        }
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getUpdatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateSmsStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE sms SET status = ?, uploadedDate = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSms(final SmsEntity sms, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSmsEntity.insertAndReturnId(sms);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateSms(final SmsEntity sms, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSmsEntity.handle(sms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateSmsStatus(final long id, final String status, final Long uploadedDate,
      final long updatedAt, final Continuation<? super Unit> arg4) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSmsStatus.acquire();
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, status);
        }
        _argIndex = 2;
        if (uploadedDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, uploadedDate);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 4;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateSmsStatus.release(_stmt);
        }
      }
    }, arg4);
  }

  @Override
  public Flow<List<SmsEntity>> getAllSms() {
    final String _sql = "SELECT * FROM sms ORDER BY receivedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sms"}, new Callable<List<SmsEntity>>() {
      @Override
      @NonNull
      public List<SmsEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "receivedDate");
          final int _cursorIndexOfUploadedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<SmsEntity> _result = new ArrayList<SmsEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmsEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpUploadedDate;
            if (_cursor.isNull(_cursorIndexOfUploadedDate)) {
              _tmpUploadedDate = null;
            } else {
              _tmpUploadedDate = _cursor.getLong(_cursorIndexOfUploadedDate);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new SmsEntity(_tmpId,_tmpSender,_tmpMessage,_tmpReceivedDate,_tmpUploadedDate,_tmpStatus,_tmpBranchId,_tmpDeviceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<SmsEntity>> getSmsByStatus(final String status) {
    final String _sql = "SELECT * FROM sms WHERE status = ? ORDER BY receivedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sms"}, new Callable<List<SmsEntity>>() {
      @Override
      @NonNull
      public List<SmsEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "receivedDate");
          final int _cursorIndexOfUploadedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<SmsEntity> _result = new ArrayList<SmsEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmsEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpUploadedDate;
            if (_cursor.isNull(_cursorIndexOfUploadedDate)) {
              _tmpUploadedDate = null;
            } else {
              _tmpUploadedDate = _cursor.getLong(_cursorIndexOfUploadedDate);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new SmsEntity(_tmpId,_tmpSender,_tmpMessage,_tmpReceivedDate,_tmpUploadedDate,_tmpStatus,_tmpBranchId,_tmpDeviceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getPendingAndFailedSms(final Continuation<? super List<SmsEntity>> arg0) {
    final String _sql = "SELECT * FROM sms WHERE status IN ('PENDING', 'FAILED')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmsEntity>>() {
      @Override
      @NonNull
      public List<SmsEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfReceivedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "receivedDate");
          final int _cursorIndexOfUploadedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfBranchId = CursorUtil.getColumnIndexOrThrow(_cursor, "branchId");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<SmsEntity> _result = new ArrayList<SmsEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmsEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final long _tmpReceivedDate;
            _tmpReceivedDate = _cursor.getLong(_cursorIndexOfReceivedDate);
            final Long _tmpUploadedDate;
            if (_cursor.isNull(_cursorIndexOfUploadedDate)) {
              _tmpUploadedDate = null;
            } else {
              _tmpUploadedDate = _cursor.getLong(_cursorIndexOfUploadedDate);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpBranchId;
            if (_cursor.isNull(_cursorIndexOfBranchId)) {
              _tmpBranchId = null;
            } else {
              _tmpBranchId = _cursor.getString(_cursorIndexOfBranchId);
            }
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new SmsEntity(_tmpId,_tmpSender,_tmpMessage,_tmpReceivedDate,_tmpUploadedDate,_tmpStatus,_tmpBranchId,_tmpDeviceId,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
